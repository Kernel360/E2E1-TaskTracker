package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.response.ResponseBoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BoardRestController {
    private final UserInfoService userInfoService;
    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<ResponseBoardDto> getBoards(
            @RequestParam String sorted,
            Model model
    ) {
        // 게시글 목록을 조회합니다.
        List<ResponseBoardDto> boards = boardService.getBoards(SortType.valueOf(sorted)).stream()
                .map(ResponseBoardDto::from)
                .toList();
        model.addAttribute("boards", boards);
        return boards;
    }

    @PutMapping("/board/{id}/finished")
    public boolean putBoardFinished(
            @PathVariable Long id,
            Model model
    ) {
        // TODO: 11/6/23 유저가 해당 게시물을 완료처리할 수 있는지 확인 프론트에서 보여주는 화면을 달리하는게 맞는듯 ㅇㅇ
        return false;
    }
}
