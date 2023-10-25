package org.fastcampus.proejct.board.repository;

import org.fastcampus.proejct.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
