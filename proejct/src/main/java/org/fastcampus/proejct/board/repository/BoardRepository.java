package org.fastcampus.proejct.board.repository;

import org.fastcampus.proejct.board.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b WHERE b.title LIKE :keyword")
    List<Board> findByKeyword(@Param("keyword") String keyword);
}
