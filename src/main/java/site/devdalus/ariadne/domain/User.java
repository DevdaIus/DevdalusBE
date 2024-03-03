package site.devdalus.ariadne.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.devdalus.ariadne.constant.LoginType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "\"user\"")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User extends Base {
    @Id
    @UuidGenerator
    @Column(name = "user_id")
    private UUID userId;

    @Setter
    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "login_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Setter
    @Column(name = "id", nullable = false)
    private String id;

    @Setter
    @Column(name = "password")
    private String password;

    @Builder
    public User(String id, String password, String nickname, LoginType loginType) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.loginType = loginType;
    }


}
