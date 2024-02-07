package site.devdalus.ariadne.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import site.devdalus.ariadne.constant.AnswerContentType;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Answer {

    @Id
    @UuidGenerator
    @Column(name = "answer_id")
    private UUID answerId;

    @ManyToOne
    @JoinColumn(name = "node_id")
    private Node node;

    @Column(name = "content_type", nullable = false)
    private AnswerContentType answerContentType;
    @Setter
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "modified_at")
    private LocalDateTime updatedAt;

    @Builder
    public Answer(AnswerContentType answerContentType, String content, Node node) {
        this.answerContentType = answerContentType;
        this.content = content;
        this.node = node;
    }

}