package org.fastcampus.proejct.friend.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserInfo user;

    @ManyToOne
    private UserInfo follower;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Friend friend)) return false;
        return Objects.equals(id, friend.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    private Friend(Long id, UserInfo user, UserInfo follower) {
        this.id = id;
        this.user = user;
        this.follower = follower;
    }

    public static Friend of(Long id, UserInfo user, UserInfo follower) {
        return new Friend(id, user, follower);
    }
}
