package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import org.fastcampus.proejct.board.converter.response.ResponseBoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BoardRestController {
    private final BoardService service;

    @GetMapping("/board/list")
    public List<ResponseBoardDto> getBoards(Model model) {
        // 게시글 목록을 조회합니다.
        List<ResponseBoardDto> boards = service.getBoards().stream()
                .map(ResponseBoardDto::from)
                .toList();
//        List<BoardDto> boards = service.getBoards();
//        model.addAttribute("boards", boards);
//        return boards;
        return null;
    }
}
