package site.devdalus.ariadne.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import site.devdalus.ariadne.constant.AnswerContentType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Answer {

    @Id
    @UuidGenerator
    @Column(name = "answer_id")
    private UUID answerId;

    @ManyToOne
    @JoinColumn(name = "node_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Node node;

    @Column(name = "content_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerContentType answerContentType;

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at", columnDefinition = "TIMESTAMP", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at", columnDefinition = "TIMESTAMP", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Answer(AnswerContentType answerContentType, String content, Node node) {
        this.answerContentType = answerContentType;
        this.content = content;
        this.node = node;
    }
}