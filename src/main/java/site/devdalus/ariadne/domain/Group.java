package site.devdalus.ariadne.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
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
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Group {


    @Id
    @UuidGenerator
    @Column(name = "group_id")
    private UUID groupId;

    @Column(name = "group_name", nullable = false)
    private String groupName;


    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Group(String groupName) {
        this.groupName = groupName;
    }
}
