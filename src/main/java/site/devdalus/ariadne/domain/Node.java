package site.devdalus.ariadne.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
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
public class Node {

    @Id
    @UuidGenerator
    @Column(name = "node_id")
    private UUID nodeId;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "parent_id")
    private UUID parentId;


    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;


    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Node(Board board, String question, UUID parentId) {
        this.board = board;
        this.parentId = parentId;
        this.question = question;
    }

}
