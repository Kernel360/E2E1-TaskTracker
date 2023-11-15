package org.fastcampus.proejct.board.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.fastcampus.proejct.base.db.model.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@ToString
@Slf4j
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Task extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String content;
    @Setter
    private String worker;
    @Setter
    private boolean isFinished;
    @Setter
    private LocalDateTime startDate;
    @Setter
    private LocalDateTime endDate;
    @Setter
    @ManyToOne(optional = false)
    private Board board;
//
//    @JoinColumn(name = "userInfoId")
//    @ManyToOne(optional = false)
//    private UserInfo userInfo;

//    todo private LocalDateTime remindDate;

    protected Task() {

    }

    private Task(
            Long id,
            String content,
            String worker,
            boolean isFinished,
            LocalDateTime startDate,
            LocalDateTime endDate
//            Board board
//            UserInfo userInfo
    ) {
        this.id = id;
        this.content = content;
        this.worker = worker;
        this.isFinished = isFinished;
        this.startDate = startDate;
        this.endDate = endDate;
//        this.board = board;
//        this.userInfo = userInfo;
    }

    public static Task of(
            Long id,
            String content,
            String worker,
            boolean isFinished,
            LocalDateTime startDate,
            LocalDateTime endDate
//            Board board
//            UserInfo userInfo
    ) {
        return new Task(
                id,
                content,
                worker,
                isFinished,
                startDate,
                endDate
//                board
//                userInfo
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task task)) return false;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
