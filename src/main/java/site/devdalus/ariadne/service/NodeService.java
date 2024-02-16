package site.devdalus.ariadne.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.dto.NodeDto;
import site.devdalus.ariadne.dto.NodeDto.*;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.BoardRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NodeService {
    private final BoardRepository boardRepository;
    private final NodeRepository nodeRepository;
    private final AnswerRepository answerRepository;
    @Autowired
    public NodeService(BoardRepository boardRepository, NodeRepository nodeRepository, AnswerRepository answerRepository) {
        this.boardRepository = boardRepository;
        this.nodeRepository = nodeRepository;
        this.answerRepository = answerRepository;
    }

    public CreateNodeResponseDto createNode(CreateNodeDto createNodeDto) {
        // TODO exception handler
        Board board = boardRepository.findById(createNodeDto.boardId).orElseThrow(() -> new RuntimeException("Node not found"));

        Node node = Node
                .builder()
                .board(board)
                .question(createNodeDto.content)
                .parentId(createNodeDto.parentId)
                .nodeDirection(createNodeDto.direction)
                .build();
        Node savedNode = nodeRepository.save(node);
        String summary = makeSummary(savedNode.getQuestion());

        return CreateNodeResponseDto
                .builder()
                .nodeId(savedNode.getNodeId())
                .summary(summary)
                .build();
    }

    // TODO summary length
    private String makeSummary(String content) {
        if (content.length() < 17) return content;
        return content.substring(0, 15) + "...";
    }

    public GetNodeResponseDto getNode(UUID nodeId) {
        // TODO exception handler
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new RuntimeException("Node not found"));

        List<UUID> children = nodeRepository
                .findByParentId(nodeId)
                .stream()
                .map(Node::getNodeId)
                .toList();

        String summary = makeSummary(node.getQuestion());

        return GetNodeResponseDto
                .builder()
                .childNodeIds(children)
                .summary(summary)
                .direction(node.getNodeDirection())
                .build();
    }

    @Transactional
    public void updateNode(UpdateNodeDto updateNodeDto, UUID nodeId) {
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new RuntimeException("Node not found"));
        node.setQuestion(updateNodeDto.content);
    }

    // TODO need transaction ??
    // TODO if transaction, recursive ok ??
    public void removeNode(UUID nodeId) {
        List<Node> children = nodeRepository.findByParentId(nodeId);
        for (Node child : children) {
            removeNode(child.getNodeId());
        }

        Optional<Node> node = nodeRepository.findById(nodeId);
        if (node.isEmpty()) return;
        nodeRepository.delete(node.get());
    }

    public GetNodeDetailResponseDto getNodeDetail(UUID nodeId) {
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new RuntimeException("Node not found"));
        List<UUID> answerIds = answerRepository
                .findByNode(node)
                .stream()
                .map(Answer::getAnswerId)
                .toList();

        return GetNodeDetailResponseDto
                .builder()
                .answerIds(answerIds)
                .content(node.getQuestion())
                .build();
    }
}
