package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.auth.converter.dto.UserPrincipal;
import org.fastcampus.proejct.board.converter.SortType;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.converter.response.ResponseBoardDto;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.global.converter.BaseResponse;
import org.fastcampus.proejct.user.service.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public BaseResponse<List<BoardDto>> getBoards(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestParam String sorted,
            Model model
    ) {
        // 게시글 목록을 조회합니다.
        List<BoardDto> boards = boardService.getBoards(userPrincipal.id(), SortType.valueOf(sorted));
        BaseResponse<List<BoardDto>> response = new BaseResponse<>(200, "정상 호출", boards);
        model.addAttribute("boards", boards);
        return response;
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
