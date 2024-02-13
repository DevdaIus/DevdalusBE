package site.devdalus.ariadne.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.validator.ValidEnum;

import java.util.UUID;

public class NodeDto {
    @NoArgsConstructor
    @Getter
    public static class CreateNodeDto {
        public UUID parentId;

        @NotNull
        public String content;

        public UUID boardId;

        @ValidEnum(enumClass = NodeDirection.class)
        public NodeDirection direction;

        @Builder
        public CreateNodeDto(UUID parentId, String content, UUID boardId, NodeDirection direction) {
            this.parentId = parentId;
            this.content = content;
            this.boardId = boardId;
            this.direction = direction;
        }
    }

    @NoArgsConstructor
    public static class CreateNodeResponseDto {
        public UUID nodeId;

        public String summary;

        @Builder
        public CreateNodeResponseDto(UUID nodeId, String summary) {
            this.nodeId = nodeId;
            this.summary = summary;
        }
    }

    @NoArgsConstructor
    public static class GetNodeResponseDto {
        public String summary;

        public UUID[] childNodeIds;

        public NodeDirection direction;

        @Builder
        public GetNodeResponseDto(String summary, UUID[] childNodeIds, NodeDirection direction) {
            this.summary = summary;
            this.childNodeIds = childNodeIds;
            this.direction = direction;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateNodeDto {
        @NotNull
        public String content;

        @Builder
        public UpdateNodeDto(String content) {
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class GetNodeDetailResponseDto {
        public String content;

        public UUID[] answerIds;

        @Builder
        public GetNodeDetailResponseDto(String content, UUID[] answerIds) {
            this.content = content;
            this.answerIds = answerIds;
        }
    }
}
