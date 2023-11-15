package org.fastcampus.proejct.board.db.repository;

import org.fastcampus.proejct.board.db.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
