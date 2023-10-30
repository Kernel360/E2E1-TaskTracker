package org.fastcampus.proejct.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fastcampus.proejct.global.domain.BaseEntity;
import org.fastcampus.proejct.user.domain.UserInfo;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @JoinColumn(name = "userInfoId")
    @ManyToOne(optional = false)
    private UserInfo userInfo;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    @Setter
    private List<Task> tasks;

    protected Board() {
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

    public static Board of(Long id, String title, String content, UserInfo userInfo) {
        return new Board(id, title, content, userInfo);
    }

    private Board(Long id, String title, String content, UserInfo userInfo) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userInfo = userInfo;
    }
}