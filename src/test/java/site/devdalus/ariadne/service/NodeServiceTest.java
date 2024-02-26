package site.devdalus.ariadne.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @PersistenceContext
    EntityManager em;

    private Board board;
    private Node rootNode;
    private Node node1;
    private Node node2;
    private Node node3;
    private Answer answer1;

    private Answer answer2;

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
        node1 = nodeRepository.save(node1);

        node2 = new Node(board, "Java2", node1.getNodeId(), NodeDirection.RIGHT);
        node2 = nodeRepository.save(node2);

        node3 = new Node(board, "Java3", node1.getNodeId(), NodeDirection.RIGHT);
        node3 = nodeRepository.save(node3);

        answer1 = new Answer(AnswerContentType.TEXT, "asdfasdfasdf", node1);
        answer1 = answerRepository.save(answer1);

        answer2 = answerRepository.save(new Answer(AnswerContentType.TEXT, "test", node3));
        em.flush();
        em.clear();
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

        Optional<Node> node = nodeRepository.findById(createNodeResponseDto.getNodeId());
        assertThat(node.get().getNodeId()).isEqualTo(createNodeResponseDto.getNodeId());
        assertThat(createNodeResponseDto.getSummary()).isEqualTo("Java is trash. ...");
    }

    @Test
    void getNode() {
        GetNodeResponseDto getNodeResponseDto = nodeService.getNode(node1.getNodeId());

        assertThat(getNodeResponseDto.getChildNodeIds()).contains(node2.getNodeId(), node3.getNodeId());
        assertThat(getNodeResponseDto.getSummary()).isEqualTo("Java1. go. pyth...");
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
        em.flush();
        em.clear();

        Optional<Node> optionalNode = nodeRepository.findById(node1.getNodeId());
        Optional<Node> optionalNode1 = nodeRepository.findById(node2.getNodeId());
        Optional<Node> optionalNode2 = nodeRepository.findById(node3.getNodeId());

        Optional<Answer> answerOptional = answerRepository.findById(answer1.getAnswerId());

        assertThat(optionalNode.isEmpty()).isTrue();
        assertThat(optionalNode1.isEmpty()).isTrue();
        assertThat(optionalNode2.isEmpty()).isTrue();
        assertThat(answerOptional.isEmpty()).isTrue();
    }

    @Test
    void getNodeDetail() {
        GetNodeDetailResponseDto nodeDetail = nodeService.getNodeDetail(node1.getNodeId());

        List<Answer> answers = answerRepository.findByNode(node1);
        assertThat(nodeDetail.getContent()).isEqualTo(node1.getQuestion());
        assertThat(nodeDetail.getAnswerIds()).contains(answers.getFirst().getAnswerId());
    }

    @Test
    void removeSpecificNode() {
        nodeService.removeNode(node3.getNodeId());

        Optional<Answer> answerOptional = answerRepository.findById(answer2.getAnswerId());
        assertThat(answerOptional.isEmpty()).isTrue();
    }
}