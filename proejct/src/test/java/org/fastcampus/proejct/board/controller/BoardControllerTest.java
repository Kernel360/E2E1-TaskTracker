package org.fastcampus.proejct.board.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.user.converter.dto.UserInfoDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

// 이게 원인
@AutoConfigureMockMvc
@DisplayName("Board Controller 테스트")
@ExtendWith(MockitoExtension.class)
public class BoardControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    @Mock
    private BoardService boardService;

    @InjectMocks
    private BoardController boardController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    @DisplayName("게시판 목록 화면 테스트")
    void testGetBoardsView() throws Exception {
        mockMvc.perform(get("/board"))
                .andExpect(status().isOk())
                .andExpect(view().name("tables"));
    }

    @Test
    @DisplayName("게시글 작성 화면 GET 테스트")
    void testGetBoardWriteView() throws Exception {
        mockMvc.perform(get("/board/write"))
                .andExpect(status().isOk())
                .andExpect(view().name("board/write"));
    }

    @Test
    @DisplayName("게시글 작성 화면 POST 테스트")
    void testPostBoardWriteView() throws Exception {
        BoardDto boardDto = createDto();
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/board/write/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(boardDto)))
                        .andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    @Test
    @DisplayName("게시글 상세 화면 테스트")
    void testGetBoardDetail() throws Exception {
        Long boardId = 1L;
        BoardDto boardDto = BoardDto.of(1L, "제목", "내용", null, null);


        when(boardService.getBoard(boardId)).thenReturn(boardDto);

        mockMvc.perform(get("/board/" + boardId))
                .andExpect(status().isOk())
                .andExpect(view().name("board/detail"));
    }

    private UserInfoDto createUserDto() {
        return UserInfoDto.of(1L, "email", "password", "nick");
    }

    private BoardDto createDto() {
        return BoardDto.of(
                1L,
                "title",
                "content",
                createUserDto(),
                List.of()
        );
    }
}