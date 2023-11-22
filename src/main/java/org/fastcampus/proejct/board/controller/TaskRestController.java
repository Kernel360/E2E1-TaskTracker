package org.fastcampus.proejct.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.Api;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.converter.request.RequestTask;
import org.fastcampus.proejct.board.service.BoardService;
import org.fastcampus.proejct.board.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/task")
@RestController
public class TaskRestController {
    private final TaskService taskService;
    private final BoardService boardService;

    //할일 목록 조회
    @GetMapping("/{boardId}/list")
    public Api<List<TaskDto>> getTasks(
            @PathVariable Long boardId
    ) {
        var tasks = taskService.getTasks(boardId);
        return Api.<List<TaskDto>>builder()
                .code(200)
                .message("정상 호출")
                .data(tasks)
                .build();
    }

    //할일 조회
    @GetMapping("/{taskId}")
    public Api<TaskDto> getTask(
            @PathVariable Long taskId
    ) {
        var task = taskService.getTask(taskId);
        return Api.<TaskDto>builder()
                .code(200)
                .message("정상 호출")
                .data(task)
                .build();
    }

    //할일 추가
    @PostMapping("/{userId}/{boardId}/add")
    public void addTask(
            @PathVariable Long userId,
            @PathVariable Long boardId,
            @RequestBody RequestTask request
    ) {
        var board = boardService.getBoard(boardId);
        log.info("add task userId : {}", userId);
        log.info("add task boardId : {}", boardId);
        log.info("add task request : {}", request);
        var task = request.toDto(board);
        taskService.updateTask(userId, board, task);
    }

    //할일 삭제
    @DeleteMapping("/{taskId}/delete")
    public void deleteTask(
            @PathVariable Long taskId
    ) {
        taskService.deleteTask(taskId);
    }
}

