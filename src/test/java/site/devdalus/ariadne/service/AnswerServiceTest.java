package site.devdalus.ariadne.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.constant.AnswerContentType;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.dto.AnswerDto.CreateAnswerDto;
import site.devdalus.ariadne.dto.AnswerDto.CreateAnswerResponseDto;
import site.devdalus.ariadne.dto.AnswerDto.GetAnswerResponseDto;
import site.devdalus.ariadne.dto.AnswerDto.UpdateAnswerDto;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.BoardRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AnswerServiceTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerService answerService;

    @PersistenceContext
    EntityManager em;

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
        node1 = nodeRepository.save(node1);

        node2 = new Node(board, "Java2", node1.getNodeId(), NodeDirection.RIGHT);
        node2 = nodeRepository.save(node2);

        node3 = new Node(board, "Java3", node1.getNodeId(), NodeDirection.RIGHT);
        node3 = nodeRepository.save(node3);

        answer1 = new Answer(AnswerContentType.TEXT, "asdfasdfasdf", node1);
        answer1 = answerRepository.save(answer1);

        em.flush();
        em.clear();
    }
    @Test
    void createAnswer() {
        CreateAnswerDto createAnswerDto = new CreateAnswerDto(node3.getNodeId(), "JVM");
        CreateAnswerResponseDto createAnswerResponseDto = answerService.createAnswer(createAnswerDto);

        List<UUID> answers = answerRepository.findByNode(node3).stream().map(Answer::getAnswerId).toList();
        assertThat(createAnswerResponseDto.getAnswerId()).isIn(answers);
    }

    @Test
    void getAnswer() {
        GetAnswerResponseDto getAnswerResponseDto = answerService.getAnswer(answer1.getAnswerId());
        assertThat(getAnswerResponseDto.getContent()).isEqualTo(answer1.getContent());
    }

    @Test
    void updateAnswer() {
        UpdateAnswerDto updateAnswerDto = new UpdateAnswerDto("JQuery");
        answerService.updateAnswer(updateAnswerDto, answer1.getAnswerId());

        Optional<Answer> answerOptional = answerRepository.findById(answer1.getAnswerId());
        assertThat(answerOptional.get().getContent()).isEqualTo(updateAnswerDto.getContent());
    }

    @Test
    void removeAnswer() {
        answerService.removeAnswer(answer1.getAnswerId());

        List<UUID> answers = answerRepository.findByNode(node1).stream().map(Answer::getAnswerId).toList();
        assertThat(answer1.getAnswerId()).isNotIn(answers);
    }
}