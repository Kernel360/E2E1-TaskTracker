package org.fastcampus.proejct.board.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.db.model.Task;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.board.db.repository.TaskRepository;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public BoardDto saveBoard(Long id, BoardDto dto) {
        UserInfo userInfo = userInfoRepository.getReferenceById(id);
        Board board = boardRepository.save(dto.toEntity(userInfo));
        return BoardDto.from(board);
    }

    public void saveBoard(BoardDto dto) {
        UserInfo userInfo = userInfoRepository.findById(dto.id()).orElseThrow();
        Board board = dto.toEntity(userInfo);
        boardRepository.save(board);
    }

    public void updateBoard(Long boardId, BoardDto dto) {
        Board board = boardRepository.getReferenceById(boardId);
        try {
            if (dto.userInfo().equals(dto.userInfo())) {
                if (dto.title() != null) board.setTitle(dto.title());
                if (dto.content() != null) board.setContent(dto.content());
                boardRepository.flush();
            }
        } catch (EntityNotFoundException e) {
            log.error("게시글을 업데이트 실패. 게시글을 찾을 수 없습니다. -dto : {}", dto);
        }
    }

    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

    public List<BoardDto> search(String keyword) {
        return boardRepository.findByKeyword(keyword).stream()
                .map(BoardDto::from)
                .toList();
    }

    public void finishedBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        board.setFinished(true);
        log.info("board service finishedBoard : {}", board);
        boardRepository.saveAndFlush(board);
    }
}

