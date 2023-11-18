package org.fastcampus.proejct.board.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.fastcampus.proejct.base.db.model.BaseEntity;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Getter
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;

    @Setter
    private String content;

    @Setter
    private boolean finished;

    @Setter
    @ManyToOne(optional = false)
    private UserInfo userInfo;

    @ToString.Exclude
    @OneToMany
    @Setter
    private List<UserInfo> members = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Setter
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
        task.setBoard(this);
    }

    public void addMember(UserInfo userInfo) {
        members.add(userInfo);
    }

    public void removeMember(UserInfo userInfo) {
        members.remove(userInfo);
    }

    protected Board() {
    }

    public Board(Long id, String title, String content, UserInfo userInfo) {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board board)) return false;
        return Objects.equals(id, board.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static Board of(Long id, String title, String content, boolean finished, UserInfo userInfo) {
        return new Board(id, title, content, finished, userInfo);
    }

    private Board(
            Long id,
            String title,
            String content,
            boolean finished,
            UserInfo userInfo
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.finished = finished;
        this.userInfo = userInfo;
    }


}