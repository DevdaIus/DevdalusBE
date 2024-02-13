package site.devdalus.ariadne.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;


import java.util.UUID;

public class BoardDto {

    @Getter
    @NoArgsConstructor(force = true)
    public static class GetBoardDto {

        @NotNull(message = "boardId is required")
        private final UUID boardId;

        @Builder
        public GetBoardDto(UUID boardId) {
            this.boardId = boardId;
        }

    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class GetBoardResponseDto {

        @NotNull(message = "rootNodeId is required")
        private final UUID rootNodeId;

        @Builder
        public GetBoardResponseDto(UUID rootNodeId) {
            this.rootNodeId = rootNodeId;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateBoardDto {

        @NotBlank
        @NotNull
        private final String subject;

        @Builder
        public CreateBoardDto(String subject) {
            this.subject = subject;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateBoardResponseDto {

        @NotNull
        private final UUID boardId;

        @Builder
        public CreateBoardResponseDto(UUID boardId) {
            this.boardId = boardId;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class UpdateBoardDto {

        @NotNull
        private final UUID boardId;

        @NotNull
        @NotBlank
        private final String subject;

        @Builder
        public UpdateBoardDto(UUID boardId, String subject) {
            this.boardId = boardId;
            this.subject = subject;
        }
    }


}
