package org.fastcampus.proejct.board.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.board.db.repository.TaskRepository;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return switch (sorted) {
            case SORT_DEFAULT -> boardRepository.searchBoardByDefault(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case SORT_ALL -> boardRepository.searchBoardByAll(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case SORT_SELF -> boardRepository.searchBoardSelf(userId).stream()
                    .map(BoardDto::from)
                    .toList();
            case SORT_FINISHED -> boardRepository.searchBoardByFinished(userId).stream()
                    .map(BoardDto::from)
                    .toList();
        };
    }

    @Transactional(readOnly = true)
    public List<BoardDto> getBoards(Long userId) {
        return boardRepository.searchBoardByDefault(userId).stream()
                .map(BoardDto::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long id) {
        return BoardDto.from(boardRepository.findById(id).orElseThrow());
    }

    public void writeBoard(Long id, BoardDto dto) {
        UserInfo userInfo = userInfoRepository.getReferenceById(id);
        boardRepository.save(dto.toEntity(userInfo));
    }

    public void updateBoard(Long id, BoardDto dto) {
        UserInfo userInfo = userInfoRepository.getReferenceById(id);
        boardRepository.save(dto.toEntity(userInfo));
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
