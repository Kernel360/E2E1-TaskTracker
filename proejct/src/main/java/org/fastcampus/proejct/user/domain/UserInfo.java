package org.fastcampus.proejct.user.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.domain.Board;
import org.fastcampus.proejct.global.domain.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
@Slf4j
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class UserInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private Boolean isBan;

    private Date exitDate;

    @Column(nullable = false)
    private Boolean adminCheck;

    @ToString.Exclude
    @OneToMany(mappedBy = "userInfo")
    private List<Board> createdBoards = new ArrayList<>();

    private UserInfo(Long id, String name, Boolean isBan, Date exitDate, Boolean adminCheck, List<Board> createdBoards) {
        this.id = id;
        this.name = name;
        this.isBan = isBan;
        this.exitDate = exitDate;
        this.adminCheck = adminCheck;
        this.createdBoards = createdBoards;
    }

    public static UserInfo of(Long id, String name, Boolean isBan, Date exitDate, Boolean adminCheck, List<Board> createdBoards) {
        return new UserInfo(id, name, isBan, exitDate, adminCheck, createdBoards);
    }

    public static UserInfo of(String name) {
        return UserInfo.of(
                null,
                name,
                false,
                null,
                false,
                List.of()
        );
    }

    protected UserInfo() {

    }

}