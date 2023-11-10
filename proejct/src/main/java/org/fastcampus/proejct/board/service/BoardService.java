package org.fastcampus.proejct.board.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserInfoDto;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.db.model.Task;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.board.db.repository.TaskRepository;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserInfoRepository userInfoRepository;
    private final TaskRepository taskRepository;
    private final ObjectMapper mapper;

    // TODO: 11/6/23 Sorted 값 별 List<Board> 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getBoards(Long userId, SortType sorted) {
        log.info("board service sorted : {}", sorted);
        return switch (sorted) {
            case SORT_DEFAULT -> boardRepository.searchBoardByDefault(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case SORT_ALL -> boardRepository.searchBoardByAll(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case SORT_SELF -> boardRepository.findByUserInfoId(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case SORT_FINISHED -> boardRepository.searchBoardByFinished(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case BOARD_ALL_FINISHED -> boardRepository.findAllByFinishedTrue().stream()
                    .map(BoardDto::from)
                    .toList();
            case BOARD_ALL -> boardRepository.findAllByFinishedFalse().stream()
                    .map(BoardDto::from)
                    .toList();
        };
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long id) {
        return BoardDto.from(boardRepository.findById(id).orElseThrow());
    }

    public void writeBoard(Long id, BoardDto dto) {
        UserInfo userInfo = userInfoRepository.getReferenceById(id);
        boardRepository.save(dto.toEntity(userInfo));
    }

    public void writeBoard(BoardDto dto) {
        UserInfo userInfo = userInfoRepository.getReferenceById(dto.userInfo().id());
        Board board = dto.toEntity(userInfo);
        boardRepository.save(board);
    }

    public void updateBoard(Long id, BoardDto dto) {
        Board preBoard = boardRepository.getReferenceById(id);

        if (dto.title() != null) preBoard.setTitle(dto.title());
        if (dto.content() != null) preBoard.setContent(dto.content());

        List<Task> tasks = dto.tasks().stream().map(TaskDto::toEntity).toList();
        List<UserInfo> members = dto.members().stream().map(UserInfoDto::toEntity).toList();

        preBoard.setTasks(tasks);
        preBoard.setMembers(members);

        boardRepository.flush();
        boardRepository.save(preBoard);
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public List<BoardDto> search(String keyword) {
        return boardRepository.findByKeyword(keyword).stream()
                .map(BoardDto::from)
                .toList();
    }
}
