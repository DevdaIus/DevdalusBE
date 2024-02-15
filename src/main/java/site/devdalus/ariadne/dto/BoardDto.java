package site.devdalus.ariadne.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import jakarta.validation.constraints.NotNull;
import site.devdalus.ariadne.domain.Board;


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

        private final String subject;

        @Builder
        public GetBoardResponseDto(UUID rootNodeId, String subject) {
            this.rootNodeId = rootNodeId;
            this.subject = subject;
        }


        public static GetBoardResponseDto toDto(Board board) {
            return new GetBoardResponseDto(board.getRootNodeId(), board.getSubject());
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

        public static CreateBoardResponseDto toDto(Board board) {
            return new CreateBoardResponseDto(board.getBoardId());
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

    @Getter
    @NoArgsConstructor(force = true)
    public static class RemoveBoardDto {
        @NotNull
        private final UUID boardId;

        @Builder
        public RemoveBoardDto(UUID boardId) {
            this.boardId = boardId;
        }
    }


}
