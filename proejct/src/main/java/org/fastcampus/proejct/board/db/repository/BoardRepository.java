package org.fastcampus.proejct.board.db.repository;

import org.fastcampus.proejct.board.db.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query("SELECT b FROM Board b WHERE b.title LIKE :keyword")
    List<Board> findByKeyword(@Param("keyword") String keyword);

//    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN b.members m\n" +
//            "WHERE (b.userInfo.id = :userId OR m.userInfo.id = :userId) AND b.isFinished = false")
//    List<Board> searchBoardByDefault(Long userId); //SORT_DEFAULT
//
//    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN b.members m\n" +
//            "WHERE (b.userInfo.id = :userId OR m.userInfo.id = :userId) AND b.isFinished = true")
//    List<Board> searchBoardByFinished(Long userId); //SORT_FINISHED
//
//    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN b.members m\n" +
//            "WHERE b.userInfo.id = :userId OR m.userInfo.id = :userId")
//    List<Board> searchBoardByFinished(Long userId); //SORT_ALL

    @Query("SELECT b FROM Board b WHERE b.userInfo.id = :userId")
    List<Board> searchBoardSelf(Long userId); // SORT_SELF

}
