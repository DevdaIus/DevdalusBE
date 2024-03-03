package site.devdalus.ariadne.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.devdalus.ariadne.constant.LoginType;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class User {
    @Id
    @UuidGenerator
    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "login_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;


    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public User(String id, String password, String nickname) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
    }


}
