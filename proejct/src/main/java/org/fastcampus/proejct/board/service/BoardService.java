package org.fastcampus.proejct.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.domain.Board;
import org.fastcampus.proejct.board.dto.BoardDto;
import org.fastcampus.proejct.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository repository;

    @Transactional(readOnly = true)
    public Optional<BoardDto> getBoard(Long id) {
        return repository.findById(id).map(BoardDto::from);
    }

    public void saveBoard(BoardDto dto) {
        repository.save(BoardDto.toEntity(dto));
    }

    public Optional<BoardDto> updateBoard(Long id) {
        Board board = repository.getReferenceById(id);
    }
}
