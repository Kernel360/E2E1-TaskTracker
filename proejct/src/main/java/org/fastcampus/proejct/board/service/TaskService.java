package org.fastcampus.proejct.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.board.db.model.Task;
import org.fastcampus.proejct.board.converter.dto.TaskDto;
import org.fastcampus.proejct.board.db.repository.BoardRepository;
import org.fastcampus.proejct.board.db.repository.TaskRepository;
import org.fastcampus.proejct.user.db.repository.UserInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final BoardRepository boardRepository;
    private final UserInfoRepository userInfoRepository;

    //할일 목록
    @Transactional(readOnly = true)
    public List<TaskDto> getTasks(Long boardId) {
        Board board = boardRepository.getReferenceById(boardId);
        return board.getTasks().stream()
                .map(TaskDto::from)
                .toList();
    }

    //할일 목록 수정겸 삭제 (리스트 교체 형식)
    public void updateTask(Long boardId, List<TaskDto> dtos) {
        Board board = boardRepository.getReferenceById(boardId);
//        UserInfo userInfo = userInfoRepository.getReferenceById(board.getUserInfo().getId());
        List<Task> tasks = dtos.stream().map(TaskDto::toEntity).toList();
        board.setTasks(tasks);
        taskRepository.saveAll(tasks);
    }
}
