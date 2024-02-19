package site.devdalus.ariadne.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.validator.ValidEnum;

import java.util.List;
import java.util.UUID;

public class NodeDto {
    private final static int SUMMARY_LENGTH = 17;
    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateNodeDto {
        @NotNull
        private final UUID parentId;

        @NotNull
        private final String content;

        @NotNull
        private final UUID boardId;

        @ValidEnum(enumClass = NodeDirection.class)
        private final NodeDirection direction;

        @Builder
        public CreateNodeDto(UUID parentId, String content, UUID boardId, NodeDirection direction) {
            this.parentId = parentId;
            this.content = content;
            this.boardId = boardId;
            this.direction = direction;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class CreateNodeResponseDto {
        private final UUID nodeId;
        private final String summary;

        @Builder
        public CreateNodeResponseDto(UUID nodeId, String summary) {
            this.nodeId = nodeId;
            this.summary = summary;
        }

        public static CreateNodeResponseDto toDto(Node node) {
            String summary = makeSummary(node.getQuestion());
            return new CreateNodeResponseDto(node.getNodeId(), summary);
        }
    }

    // TODO summary length
    private static String makeSummary(String content) {
        if (content.length() < SUMMARY_LENGTH) return content;
        return content.substring(0, SUMMARY_LENGTH - 2) + "...";
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class GetNodeResponseDto {
        private final String summary;
        private final List<UUID> childNodeIds;
        private final NodeDirection direction;

        @Builder
        public GetNodeResponseDto(String summary, List<UUID> childNodeIds, NodeDirection direction) {
            this.summary = summary;
            this.childNodeIds = childNodeIds;
            this.direction = direction;
        }

        public static GetNodeResponseDto toDto(Node node, List<Node> nodes) {
            String summary = makeSummary(node.getQuestion());
            List<UUID> children = nodes
                    .stream()
                    .map(Node::getNodeId)
                    .toList();
            return new GetNodeResponseDto(summary, children, node.getNodeDirection());
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class UpdateNodeDto {
        @NotNull
        private final String content;

        @Builder
        public UpdateNodeDto(String content) {
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(force = true)
    public static class GetNodeDetailResponseDto {
        private final String content;
        private final List<UUID> answerIds;

        @Builder
        public GetNodeDetailResponseDto(String content, List<UUID> answerIds) {
            this.content = content;
            this.answerIds = answerIds;
        }

        public static GetNodeDetailResponseDto toDto(Node node, List<Answer> answers) {
            List<UUID> answerIds = answers
                    .stream()
                    .map(Answer::getAnswerId)
                    .toList();
            return new GetNodeDetailResponseDto(node.getQuestion(), answerIds);
        }
    }
}
