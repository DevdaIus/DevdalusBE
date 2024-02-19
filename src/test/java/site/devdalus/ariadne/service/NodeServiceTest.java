package site.devdalus.ariadne.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.constant.AnswerContentType;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.dto.NodeDto.CreateNodeDto;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.BoardRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static site.devdalus.ariadne.dto.NodeDto.*;

@SpringBootTest
@Transactional
class NodeServiceTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private NodeService nodeService;

    private Board board;
    private Node rootNode;
    private Node node1;
    private Node node2;
    private Node node3;
    private Answer answer1;

    @BeforeEach
    void init() {
        board = Board
                .builder()
                .subject("Java")
                .build();
        boardRepository.save(board);

        rootNode = Node
                .builder()
                .nodeDirection(NodeDirection.CENTER)
                .question("Java")
                .board(board)
                .build();
        nodeRepository.save(rootNode);

        node1 = new Node(board, "Java1. go. python. javascript. c++. let's go", rootNode.getNodeId(), NodeDirection.RIGHT);
        nodeRepository.save(node1);

        node2 = new Node(board, "Java2", node1.getNodeId(), NodeDirection.RIGHT);
        nodeRepository.save(node2);

        node3 = new Node(board, "Java3", node1.getNodeId(), NodeDirection.RIGHT);
        nodeRepository.save(node3);

        answer1 = new Answer(AnswerContentType.TEXT, "asdfasdfasdf", node1);
        answerRepository.save(answer1);
    }

    @Test
    void createNode() {
        CreateNodeDto createNodeDto = CreateNodeDto
                .builder()
                .parentId(rootNode.getNodeId())
                .direction(NodeDirection.CENTER)
                .boardId(board.getBoardId())
                .content("Java is trash. Python is God. Thank you.")
                .build();

        CreateNodeResponseDto createNodeResponseDto = nodeService.createNode(createNodeDto);

        Optional<Node> node = nodeRepository.findById(createNodeResponseDto.nodeId);
        assertThat(node.get().getNodeId()).isEqualTo(createNodeResponseDto.nodeId);
        assertThat(createNodeResponseDto.summary).isEqualTo("Java is trash. ...");
    }

    @Test
    void getNode() {
        GetNodeResponseDto getNodeResponseDto = nodeService.getNode(node1.getNodeId());

        assertThat(getNodeResponseDto.childNodeIds).contains(node2.getNodeId(), node3.getNodeId());
        assertThat(getNodeResponseDto.summary).isEqualTo("Java1. go. pyth...");
    }

    @Test
    void updateNode() {
        String updateContent = "update content";
        UpdateNodeDto updateNodeDto = new UpdateNodeDto(updateContent);
        nodeService.updateNode(updateNodeDto, node1.getNodeId());

        Optional<Node> node = nodeRepository.findById(node1.getNodeId());
        assertThat(node.get().getQuestion()).isEqualTo(updateContent);
    }

    @Test
    void removeNode() {
        nodeService.removeNode(node1.getNodeId());

        Optional<Node> optionalNode1 = nodeRepository.findById(node2.getNodeId());
        Optional<Node> optionalNode2 = nodeRepository.findById(node3.getNodeId());

        assertThat(optionalNode1.isEmpty()).isTrue();
        assertThat(optionalNode2.isEmpty()).isTrue();
    }

    @Test
    void getNodeDetail() {
        GetNodeDetailResponseDto nodeDetail = nodeService.getNodeDetail(node1.getNodeId());

        List<Answer> answers = answerRepository.findByNode(node1);
        assertThat(nodeDetail.content).isEqualTo(node1.getQuestion());
        assertThat(nodeDetail.answerIds).contains(answers.getFirst().getAnswerId());
    }
}