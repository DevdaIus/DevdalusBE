package site.devdalus.ariadne.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class AnswerDto extends BaseDto {
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
