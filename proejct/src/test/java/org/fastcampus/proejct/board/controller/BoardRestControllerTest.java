package org.fastcampus.proejct.board.controller;

import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.fastcampus.proejct.global.security.FormDataEncoder;
import org.fastcampus.proejct.global.security.SecurityConfig;
import org.fastcampus.proejct.global.security.TestSecurityConfig;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import({TestSecurityConfig.class, FormDataEncoder.class})
@WebMvcTest(BoardRestController.class)
class BoardRestControllerTest {
    private final MockMvc mvc;
    @MockBean
    private BoardService service;
    @MockBean
    private TaskService taskService;

    @MockBean
    private FormDataEncoder encoder;

    public BoardRestControllerTest(
            @Autowired MockMvc mvc,
            @Autowired FormDataEncoder encoder
    ) {
        this.mvc = mvc;
        this.encoder = encoder;
    }

    @WithMockUser(username = "tester", roles = "USER")
    @DisplayName("[GET] 게시판 조회")
    @Test
    void test() throws Exception {
        //given

        //when
        mvc.perform(get("/board/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("boards"))
                .andExpect(model().attribute("t1", "t1"))
                .andExpect(model().attribute("t2", "t2"))
                .andExpect(model().attribute("t3", "t3"));
        //then
        then(service).should().getBoards();
    }

}