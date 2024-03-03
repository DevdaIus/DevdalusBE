package site.devdalus.ariadne.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.devdalus.ariadne.constant.PermissionName;

import java.util.UUID;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Permission extends Base {

    @Id
    @UuidGenerator
    @Column(name = "permission_id")
    private UUID permissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "permission_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private PermissionName permissionName;

    @Builder
    public Permission(Role role, PermissionName permissionName) {
        this.role = role;
        this.permissionName = permissionName;
    }

}
