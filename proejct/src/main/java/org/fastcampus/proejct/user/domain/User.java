package org.fastcampus.proejct.user.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.board.domain.Board;
import org.fastcampus.proejct.global.domain.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@ToString
@Entity
@Slf4j
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    private Date regist_date;

    private Date update_date;

    private Boolean accessCheck;

    private Date exitDate;

    @Column(nullable = false)
    private Boolean adminCheck;

    public static User of(Long id, String password, String name, Date regist_date, Date update_date, Boolean accessCheck, Date exitDate, Boolean adminCheck) {
        return new User(id, password, name, regist_date, update_date, accessCheck, exitDate, adminCheck);
    }
}