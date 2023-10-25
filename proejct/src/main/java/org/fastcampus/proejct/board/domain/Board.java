package org.fastcampus.proejct.board.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.fastcampus.proejct.global.domain.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter
    @Column(nullable = false)
    private String title;
    @Setter
    private String content;

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

    public static Board of(Long id, String title, String content) {
        return new Board(id, title, content);
    }

    private Board(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}