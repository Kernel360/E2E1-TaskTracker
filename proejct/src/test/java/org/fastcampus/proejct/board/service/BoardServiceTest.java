package org.fastcampus.proejct.board.service;

import org.fastcampus.proejct.board.domain.Board;
import org.fastcampus.proejct.board.dto.BoardDto;
import org.fastcampus.proejct.board.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

/**
 * Given
 * - 테스트를 위해 주어진 상태
 * - 테스트 대상에게 주어진 조건
 * - 테스트가 동작하기 위해 주어진 환경
 * When
 * - 테스트 대상에게 가해진 어떠한 상태
 * - 테스트 대상에게 주어진 어떠한 조건
 * - 테스트 대상의 상태를 변경시키기 위한 환경
 * Then
 * - 앞선 과정의 결과
 */
@DisplayName("게시판 관련 비즈니스 로직 테스트")
@ExtendWith(MockitoExtension.class)
class
BoardServiceTest {
    @InjectMocks
    private BoardService service;
    @Mock
    private BoardRepository repository;

    @DisplayName("게시글 상세 화면 호출")
    @Test
    void testGetBoard() {
        //given
        Long id = 1L;
        Board expected = createdBoard();
        given(repository.findById(id)).willReturn(Optional.of(expected));
        //when
        Optional<BoardDto> actual = service.getBoard(id);
        //then
        assertEquals(BoardDto.from(expected), actual.get());
        then(repository).should().findById(id);
    }

    @DisplayName("게시글 상세 화면 생성")
    @Test
    void testCreatedBoard() {
        //given
        Board expected = createdBoard();
        given(repository.save(expected)).willReturn(null);
        //when
        service.saveBoard(BoardDto.from(expected));
        //then
        then(repository).should().save(any(Board.class));
    }

    @DisplayName("게시글 상세 화면 수정 ")
    @Test
    void testUpdatedBoard() {
        //given
        Long id = 1L;
        Board expected = createdBoard();
        given(repository.save(expected)).willReturn(null);
        //when
        Board actual = repository.findById(id).orElseThrow();
        actual.setContent("내용 변경됨");
        service.updateBoard();
        //then

        then(repository).should().save(any(Board.class));
    }

    @DisplayName("게시글 상세 화면 삭제")
    @Test
    void testDeletedBoard() {
        //given

        //when

        //then
    }

    private Board createdBoard() {
        return Board.of(1L, "title", "content");
    }
}