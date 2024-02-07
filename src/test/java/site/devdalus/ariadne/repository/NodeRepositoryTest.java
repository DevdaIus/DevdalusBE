package site.devdalus.ariadne.repository;


import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles("test")
public class NodeRepositoryTest {

    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private BoardRepository boardRepository;

    private Board board;

    @BeforeEach
    public void beforeEach() {
        board = new Board("Javascript");
        boardRepository.save(board);
    }


    @Test
    public void save() {
        //given
        Node node = new Node(board, "What is hoisting?", null);

        //when
        nodeRepository.save(node);
        Node foundNode = nodeRepository.findAll().getFirst();

        //then
        assertThat(node.getQuestion()).isEqualTo(foundNode.getQuestion());
    }

    @Test
    public void update() {

        Node node = new Node(board, "What is hoisting?", null);
        nodeRepository.save(node);
        Node foundNode = nodeRepository.findAll().getFirst();
        foundNode.setQuestion("What is execution context?");
        nodeRepository.save(foundNode);
        Node setNode = nodeRepository.findAll().getFirst();
        assertThat(setNode.getQuestion()).isEqualTo(foundNode.getQuestion());
    }

    @Test
    public void delete() {
        Node node = new Node(board, "What is hoisting?", null);
        nodeRepository.save(node);
        Node foundNode = nodeRepository.findAll().getFirst();
        nodeRepository.delete(foundNode);
        List<Node> nodes = nodeRepository.findAll();
        assertThat(nodes.size()).isEqualTo(0);
    }
}
