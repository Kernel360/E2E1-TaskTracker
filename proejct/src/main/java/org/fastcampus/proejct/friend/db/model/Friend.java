package org.fastcampus.proejct.friend.db.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.fastcampus.proejct.user.db.model.UserInfo;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Friend {

    @Id
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "follow_id")
    private UserInfo follow;

}
