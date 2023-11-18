package org.fastcampus.proejct.board.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.converter.Api;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.converter.request.RequestTask;
import org.fastcampus.proejct.board.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/task")
@RestController
public class TaskController {
    private final TaskService taskService;

    //할일 수정
    @PostMapping("/{boardId}/update")
    public List<TaskDto> postTasksUpdate(
            @PathVariable Long boardId
    ) {
        return taskService.getTasks(boardId);
    }

    //할일 목록 조회
    @GetMapping("/{boardId}")
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

    @PutMapping("/{boardId}/add")
    public void putTask(
            @PathVariable Long boardId,
            @RequestBody List<RequestTask> request
    ) {
        var dtos = request.stream()
                .map(RequestTask::toDto)
                .toList();
        taskService.updateTask(boardId, dtos);
    }

    @DeleteMapping("/{boardId}/delete")
    public void deleteTask(
            @PathVariable Long boardId,
            @RequestBody List<TaskDto> dtos
    ) {
        taskService.deleteTask(boardId, dtos);
    }
}
