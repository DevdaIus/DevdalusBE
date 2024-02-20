package site.devdalus.ariadne.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.devdalus.ariadne.constant.NodeDirection;

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
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @Column(name = "parent_id")
    private UUID parentId;

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    @Column(name = "node_direction", nullable = false)
    @Enumerated(EnumType.STRING)
    private NodeDirection nodeDirection;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Node(Board board, String question, UUID parentId, NodeDirection nodeDirection) {
        this.board = board;
        this.parentId = parentId;
        this.question = question;
        this.nodeDirection = nodeDirection;
    }

}
