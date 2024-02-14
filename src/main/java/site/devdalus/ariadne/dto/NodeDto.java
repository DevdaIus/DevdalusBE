package site.devdalus.ariadne.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.validator.ValidEnum;

import java.util.UUID;

public class NodeDto {
    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateNodeDto {
        public final UUID parentId;

        @NotNull
        public final String content;

        public final UUID boardId;

        @ValidEnum(enumClass = NodeDirection.class)
        public final NodeDirection direction;

        @Builder
        public CreateNodeDto(UUID parentId, String content, UUID boardId, NodeDirection direction) {
            this.parentId = parentId;
            this.content = content;
            this.boardId = boardId;
            this.direction = direction;
        }
    }

    @NoArgsConstructor(force = true)
    public static class CreateNodeResponseDto {
        public final UUID nodeId;
        public final String summary;

        @Builder
        public CreateNodeResponseDto(UUID nodeId, String summary) {
            this.nodeId = nodeId;
            this.summary = summary;
        }
    }

    @NoArgsConstructor(force = true)
    public static class GetNodeResponseDto {
        public final String summary;
        public final UUID[] childNodeIds;
        public final NodeDirection direction;

        @Builder
        public GetNodeResponseDto(String summary, UUID[] childNodeIds, NodeDirection direction) {
            this.summary = summary;
            this.childNodeIds = childNodeIds;
            this.direction = direction;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class UpdateNodeDto {
        @NotNull
        public final String content;

        @Builder
        public UpdateNodeDto(String content) {
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class GetNodeDetailResponseDto {
        public final String content;

        public final UUID[] answerIds;

        @Builder
        public GetNodeDetailResponseDto(String content, UUID[] answerIds) {
            this.content = content;
            this.answerIds = answerIds;
        }
    }
}
