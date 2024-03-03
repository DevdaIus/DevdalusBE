package site.devdalus.ariadne.domain;


import jakarta.persistence.*;
import lombok.*;
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
public class Board extends Base {

    @Id
    @UuidGenerator
    @Column(name = "board_id")
    private UUID boardId;

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String subject;

    @UuidGenerator
    @Column(name = "root_node_id")
    private UUID rootNodeId;

    @Builder
    public Board(String subject) {
        this.subject = subject;
    }
}

