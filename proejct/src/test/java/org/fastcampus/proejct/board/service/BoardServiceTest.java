package org.fastcampus.proejct.board.service;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

/**
 * given() 블록은 테스트에 필요한 가짜 객체와 조건을 설정합니다.
 * when() 블록은 테스트 대상 메서드를 호출합니다.
 * then() 블록은 테스트 대상 메서드의 결과를 검증합니다.
 */
@DisplayName("게시판 관련 비즈니스 로직 테스트")
@ExtendWith(MockitoExtension.class)
class BoardServiceTest {
    @InjectMocks
    private BoardService service;
    @Mock
    private BoardRepository boardRepository;
    @Mock
    private UserInfoRepository userInfoRepository;

    @DisplayName("게시글 상세 화면 호출")
    @Test
    void testGetBoard() {
        //given
        Long id = 1L;
        Board board = board();
        given(boardRepository.findById(id)).willReturn(Optional.of(board));
        //when

        BoardDto actual = service.getBoard(id);
        //then
        assertThat(actual)
                .hasFieldOrPropertyWithValue("title", "title")
                .hasFieldOrPropertyWithValue("content", "content");
        then(boardRepository).should().findById(id);
    }

    @DisplayName("게시글 상세 화면 생성")
    @Test
    void testCreatedBoard() {
        //given
        Board expected = board();
        UserInfo userInfo = user();
        given(boardRepository.save(expected)).willReturn(null);
        //when
        service.writeBoard(BoardDto.from(expected));
        //then
        then(boardRepository).should().save(any(Board.class));
    }

    @DisplayName("게시글 상세 화면 수정 ")
    @Test
    void testUpdatedBoard() {
        //given
        Long id = 1L;
        Board board = board();
        given(boardRepository.findById(id)).willReturn(Optional.of(board));
        //when
        board.setContent("내용 변경됨");
        service.updateBoard(id, BoardDto.from(board));
        boardRepository.flush();
        //then
        Board actual = boardRepository.findById(id).orElseThrow();
        assertThat(actual)
                .hasFieldOrPropertyWithValue("title", "title")
                .hasFieldOrPropertyWithValue("content", "내용 변경됨");
        then(boardRepository).should().findById(id);
    }

    @DisplayName("게시글 상세 화면 삭제")
    @Test
    void testDeletedBoard() {
        //given
        Long id = 1L;
        boardRepository.save(board());
        //when
        service.deleteBoard(id);
        //then
        assertEquals(0, boardRepository.count());
    }

    private Board board() {
        return Board.of(1L, "title", "content", user());
    }

    private UserInfo user() {
        return UserInfo.of("NNname");
    }
}