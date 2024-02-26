package site.devdalus.ariadne.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.devdalus.ariadne.domain.Answer;

import java.util.UUID;

public class AnswerDto {
    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateAnswerDto {
        private final UUID nodeId;
        private final String content;

        @Builder
        public CreateAnswerDto(UUID nodeId, String content) {
            this.nodeId = nodeId;
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateAnswerResponseDto {
        private final UUID answerId;

        @Builder
        public CreateAnswerResponseDto(UUID answerId) {
            this.answerId = answerId;
        }

        public static CreateAnswerResponseDto toDto(Answer answer) {
            return new CreateAnswerResponseDto(answer.getAnswerId());
        }
    }

    @Getter
    public static class GetAnswerResponseDto {
        private final String content;

        @Builder
        public GetAnswerResponseDto(String content) {
            this.content = content;
        }

        public static GetAnswerResponseDto toDto(String content) {
            return new GetAnswerResponseDto(content);
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class UpdateAnswerDto {
        private final String content;
        @Builder
        public UpdateAnswerDto(String content) {
            this.content = content;
        }
    }
}
