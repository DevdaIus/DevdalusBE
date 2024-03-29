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
public class Answer extends Base {

    @Id
    @UuidGenerator
    @Column(name = "answer_id")
    private UUID answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "node_id")
    private Node node;

    @Column(name = "content_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AnswerContentType answerContentType;

    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Builder
    public Answer(AnswerContentType answerContentType, String content, Node node) {
        this.answerContentType = answerContentType;
        this.content = content;
        this.node = node;
    }
}