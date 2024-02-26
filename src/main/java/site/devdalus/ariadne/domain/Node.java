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
import site.devdalus.ariadne.constant.NodeDirection;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Node extends Base {

    @Id
    @UuidGenerator
    @Column(name = "node_id")
    private UUID nodeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(name = "parent_id")
    private UUID parentId;

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    @Column(name = "node_direction", nullable = false)
    @Enumerated(EnumType.STRING)
    private NodeDirection nodeDirection;

    @Builder
    public Node(Board board, String question, UUID parentId, NodeDirection nodeDirection) {
        this.board = board;
        this.parentId = parentId;
        this.question = question;
        this.nodeDirection = nodeDirection;
    }

}
