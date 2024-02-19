package site.devdalus.ariadne.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import site.devdalus.ariadne.constant.AnswerContentType;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;
import site.devdalus.ariadne.dto.NodeDto.CreateNodeDto;
import site.devdalus.ariadne.dto.NodeDto.UpdateNodeDto;
import site.devdalus.ariadne.repository.AnswerRepository;
import site.devdalus.ariadne.repository.BoardRepository;
import site.devdalus.ariadne.repository.NodeRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class NodeControllerTest {
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
                .nodeDirection(NodeDirection.RIGHT)
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
    void createTest() throws Exception {
        CreateNodeDto createNodeDto = CreateNodeDto.builder()
                .content("Java1. go. python. javascript. c++. let's go")
                .boardId(board.getBoardId())
                .direction(NodeDirection.LEFT)
                .parentId(rootNode.getNodeId())
                .build();

        MvcResult result = mockMvc
                .perform(post("/v1/node")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createNodeDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.summary").value("Java1. go. pyth..."))
                .andReturn();
    }

    @Test
    void getTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/v1/node/" + node1.getNodeId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.childNodeIds[0]").value(node2.getNodeId().toString()))
                .andExpect(jsonPath("$.childNodeIds[1]").value(node3.getNodeId().toString()))
                .andReturn();
    }

    @Test
    void updateTest() throws Exception {
        UpdateNodeDto updateNodeDto = UpdateNodeDto
                .builder()
                .content("asdf")
                .build();

        MvcResult result = mockMvc
                .perform(patch("/v1/node/" + node1.getNodeId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateNodeDto)))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void removeTest() throws Exception {
        MvcResult result = mockMvc
                .perform(delete("/v1/node/" + node1.getNodeId()))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void getNodeDetailTest() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("/v1/node/").append(node1.getNodeId()).append("/detail");
        MvcResult result = mockMvc
                .perform(get(sb.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(node1.getQuestion()))
                .andExpect(jsonPath("$.answerIds[0]").value(answer1.getAnswerId().toString()))
                .andReturn();
    }
}