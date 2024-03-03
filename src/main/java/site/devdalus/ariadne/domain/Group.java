package site.devdalus.ariadne.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Table(name = "\"group\"")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Group extends Base {
    
    @Id
    @UuidGenerator
    @Column(name = "group_id")
    private UUID groupId;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @Builder
    public Group(String groupName) {
        this.groupName = groupName;
    }
}
