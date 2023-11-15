//package org.fastcampus.proejct.board.controller;
//
//import org.fastcampus.proejct.board.converter.dto.BoardDto;
//import org.fastcampus.proejct.board.service.BoardService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//
//@DisplayName("Board Controller 테스트")
//@ExtendWith(MockitoExtension.class)
//public class BoardControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private BoardService boardService;
//
//    @InjectMocks
//    private BoardController boardController;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
//    }
//
//    @Test
//    @DisplayName("게시판 목록 화면 테스트")
//    void testGetBoardsView() throws Exception {
//        mockMvc.perform(get("/board"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("tables"));
//    }
//
//    @Test
//    @DisplayName("게시글 작성 화면 테스트")
//    void testGetBoardWriteView() throws Exception {
//        mockMvc.perform(get("/board/write"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("board/write"));
//    }
//
//
//    @Test
//    @DisplayName("게시글 상세 화면 테스트")
//    void testGetBoardDetail() throws Exception {
//        Long boardId = 1L;
//        BoardDto boardDto = BoardDto.of(1L, "제목", "내용", null, null);
//
//
//        when(boardService.getBoard(boardId)).thenReturn(boardDto);
//
//        mockMvc.perform(get("/board/" + boardId))
//                .andExpect(status().isOk())
//                .andExpect(view().name("board/detail"));
//    }
//}