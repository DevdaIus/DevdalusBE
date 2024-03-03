package site.devdalus.ariadne.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Role {


    @Id
    @UuidGenerator
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "role_name")
    private String roleName;

    @Builder
    public Role(String roleName) {
        this.roleName = roleName;
    }


}
