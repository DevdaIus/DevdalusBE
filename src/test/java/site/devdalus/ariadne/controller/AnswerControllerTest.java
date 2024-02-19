package site.devdalus.ariadne.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.constant.AnswerContentType;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.BoardRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static site.devdalus.ariadne.dto.AnswerDto.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class AnswerControllerTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Board board;
    private Node rootNode;
    private Node node1;

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
                .nodeDirection(NodeDirection.RIGHT)
                .question("Java")
                .board(board)
                .build();
        nodeRepository.save(rootNode);

        node1 = new Node(board, "Java1. go. python. javascript. c++. let's go", rootNode.getNodeId(), NodeDirection.CENTER);
        nodeRepository.save(node1);

        answer1 = new Answer(AnswerContentType.TEXT, "asdfasdfasdf", node1);
        answerRepository.save(answer1);
    }

    @Test
    void createAnswer() throws Exception {
        CreateAnswerDto createAnswerDto = CreateAnswerDto
                .builder()
                .nodeId(node1.getNodeId())
                .content("JVM")
                .build();

        mockMvc
                .perform(post("/v1/answer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAnswerDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void getAnswer() throws Exception {
        mockMvc
                .perform(get("/v1/answer/" + answer1.getAnswerId()))
                .andExpect(status().isOk());
    }

    @Test
    void updateAnswer() throws Exception {
        UpdateAnswerDto updateAnswerDto = UpdateAnswerDto
                .builder()
                .content("asdf")
                .build();
        mockMvc
                .perform(patch("/v1/answer/" + answer1.getAnswerId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateAnswerDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void removeAnswer() throws Exception {
        mockMvc
                .perform(delete("/v1/answer/" + answer1.getAnswerId()))
                .andExpect(status().isNoContent());
    }
}