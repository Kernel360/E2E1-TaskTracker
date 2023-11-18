package org.fastcampus.proejct.follow.db.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
@ToString
@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private Long follower;
    @Setter
    private Long following;

    private Follow(Long id, Long follower, Long following) {
        this.id = id;
        this.follower = follower;
        this.following = following;
    }

    public static Follow of(Long id, Long follower, Long following) {
        return new Follow(id, follower, following);
    }

    public static Follow of(Long follower, Long following) {
        return Follow.of(
                null,
                follower,
                following
        );
    }
}
