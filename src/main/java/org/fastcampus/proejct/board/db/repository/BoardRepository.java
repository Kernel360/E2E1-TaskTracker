package org.fastcampus.proejct.board.db.repository;

import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("SELECT b FROM Board b WHERE b.title LIKE :keyword")
    List<Board> findByKeyword(@Param("keyword") String keyword);

    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN b.members m\n" +
            "WHERE (b.userInfo.id = :userId OR m.id = :userId) AND b.finished = false")
    List<Board> searchBoardByDefault(@Param("userId") Long userId); //SORT_DEFAULT

    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN b.members m\n" +
            "WHERE (b.userInfo.id = :userId OR m.id = :userId) AND b.finished = true")
    List<Board> searchBoardByFinished(@Param("userId") Long userId); //SORT_FINISHED

    @Query("SELECT DISTINCT b FROM Board b LEFT JOIN b.members m\n" +
            "WHERE b.userInfo.id = :userId OR m.id = :userId")
    List<Board> searchBoardByAll(@Param("userId") Long userId); //SORT_ALL

    List<Board> findByUserInfoId(Long id);//SORT_SELF

    List<Board> findAllByFinishedTrue(); //BOARD_ALL_FINISHED

    List<Board> findAllByFinishedFalse(); //BOARD_ALL

    void deleteBoardByIdAndMembersContaining(Long id, UserInfo userInfo);
}
