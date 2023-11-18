package org.fastcampus.proejct.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.converter.dto.BoardDto;
import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.db.model.Task;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.board.db.repository.TaskRepository;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;
    private final UserInfoRepository userInfoRepository;

    //할일 목록 조회
    @Transactional(readOnly = true)
    public List<TaskDto> getTasks(Long boardId) {
        Board board = boardRepository.getReferenceById(boardId);
        return board.getTasks().stream()
                .map(TaskDto::from)
                .toList();
    }

    //할일 조회
    @Transactional(readOnly = true)
    public TaskDto getTask(Long boardId) {
        Task task = taskRepository.getReferenceById(boardId);
        return TaskDto.from(task);
    }

    //할일 추가
    public void updateTask(Long userId, BoardDto boardDto, TaskDto taskDto) {
        UserInfo user = userInfoRepository.getReferenceById(userId);
        Board board = boardDto.toEntity(user);
        Task task = taskDto.toEntity(user, board);
        log.info("tasks user update : {}", user);
        log.info("tasks board update : {}", board);
        log.info("tasks tasks update : {}", task);
        board.addTask(task);
        taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
