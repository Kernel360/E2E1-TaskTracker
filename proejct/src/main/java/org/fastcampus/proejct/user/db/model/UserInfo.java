package org.fastcampus.proejct.user.db.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.db.model.Board;
import org.fastcampus.proejct.friend.db.model.Friend;
import org.fastcampus.proejct.home.db.model.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private Boolean isBan;

    private Date exitDate;

    @Column(nullable = false)
    private Boolean adminCheck;

    @ToString.Exclude
    @OneToMany(mappedBy = "userInfo")
    private List<Board> createdBoards = new ArrayList<>();


    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Friend> friends = new ArrayList<>();
    private UserInfo(Long id, String email, String password, String name, Boolean isBan, Date exitDate, Boolean adminCheck) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.isBan = isBan;
        this.exitDate = exitDate;
        this.adminCheck = adminCheck;
    }

    public static UserInfo of(
            Long id,
            String email,
            String password,
            String name,
            Boolean isBan,
            Date exitDate,
            Boolean adminCheck
    ) {
        return new UserInfo(id, email, password, name, isBan, exitDate, adminCheck);
    }

    public static UserInfo of(String email, String password, String name) {
        return UserInfo.of(
                null,
                email,
                password,
                name,
                false,
                null,
                false
        );
    }

    public static UserInfo of(String name) {
        return UserInfo.of(
                null,
                "email@email",
                "password",
                name,
                false,
                null,
                false
        );
    }

    protected UserInfo() {

    }
}