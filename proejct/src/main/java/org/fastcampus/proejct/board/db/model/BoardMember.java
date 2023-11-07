//package org.fastcampus.proejct.board.db.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import lombok.extern.slf4j.Slf4j;
//import org.fastcampus.proejct.home.db.model.BaseEntity;
//import org.fastcampus.proejct.user.db.model.UserInfo;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//
//import java.util.Objects;
//
//@ToString
//@Table(indexes = {
//        @Index(columnList = "createdAt"),
//        @Index(columnList = "createdBy")
//})
//@Entity
//@Slf4j
//@EntityListeners(AuditingEntityListener.class)
//@Getter
//public class BoardMember extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Setter
//    @ManyToOne
//    private Board board;
//
//    @Setter
//    @ManyToOne
//    private UserInfo userInfo;
//
//    protected BoardMember() {
//    }
//
//    private BoardMember(Long id, Board board, UserInfo userInfo) {
//        this.id = id;
//        this.board = board;
//        this.userInfo = userInfo;
//    }
//
//    public static BoardMember of(Long id, Board board, UserInfo userInfo) {
//        return new BoardMember(id, board, userInfo);
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof BoardMember that)) return false;
//        return Objects.equals(id, that.id);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}
