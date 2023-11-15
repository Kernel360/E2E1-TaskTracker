package org.fastcampus.proejct.board.controller;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.service.TaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TaskController {
    private final TaskService taskService;

    //할일 수정
    @GetMapping("/board/{id}/tasks")
    public List<TaskDto> postTasksUpdate(
            @PathVariable Long id,
            Model model
    ) {
        return taskService.getTasks(id);
    }

}
