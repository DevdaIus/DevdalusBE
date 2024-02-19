package site.devdalus.ariadne.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.dto.NodeDto.*;
import site.devdalus.ariadne.exception.ResourceNotExistException;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.BoardRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
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

    @Transactional
    public CreateNodeResponseDto createNode(CreateNodeDto createNodeDto) {
        // TODO exception handler
        Board board = boardRepository.findById(createNodeDto.getBoardId()).orElseThrow(() -> new ResourceNotExistException("Node not found."));

        Node node = Node
                .builder()
                .board(board)
                .question(createNodeDto.getContent())
                .parentId(createNodeDto.getParentId())
                .nodeDirection(createNodeDto.getDirection())
                .build();
        Node savedNode = nodeRepository.save(node);

        return CreateNodeResponseDto.toDto(savedNode);
    }

    public GetNodeResponseDto getNode(UUID nodeId) {
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new ResourceNotExistException("Node not found."));

        List<Node> nodes = nodeRepository.findByParentId(nodeId);

        return GetNodeResponseDto.toDto(node, nodes);
    }

    @Transactional
    public void updateNode(UpdateNodeDto updateNodeDto, UUID nodeId) {
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new ResourceNotExistException("Node not found."));
        node.setQuestion(updateNodeDto.getContent());
    }

    // TODO need transaction ??
    // TODO if transaction, recursive ok ??
    @Transactional
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
        Node node = nodeRepository.findById(nodeId).orElseThrow(() -> new ResourceNotExistException("Node not found."));
        List<Answer> answers = answerRepository.findByNode(node);

        return GetNodeDetailResponseDto.toDto(node, answers);
    }
}
