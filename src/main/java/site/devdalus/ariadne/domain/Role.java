package site.devdalus.ariadne.domain;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.devdalus.ariadne.constant.RoleName;

import java.util.UUID;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Role extends Base {


    @Id
    @UuidGenerator
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "role_name", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Builder
    public Role(RoleName roleName) {
        this.roleName = roleName;
    }


}
