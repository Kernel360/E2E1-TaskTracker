package org.fastcampus.proejct.board.repository;

import org.fastcampus.proejct.board.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
