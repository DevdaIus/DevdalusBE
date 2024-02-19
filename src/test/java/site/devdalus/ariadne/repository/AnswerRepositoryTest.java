package site.devdalus.ariadne.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.constant.AnswerContentType;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.domain.Answer;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.domain.Node;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class AnswerRepositoryTest {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private BoardRepository boardRepository;

    private Node node;

    @BeforeEach
    public void flushRepository() {
        Board board = Board
                .builder()
                .subject("Java")
                .build();
        boardRepository.save(board);

        node = Node
                .builder()
                .question("question test")
                .board(board)
                .nodeDirection(NodeDirection.RIGHT)
                .build();
        nodeRepository.save(node);
    }


    @Test
    public void save() {
        // given
        Answer answer = Answer
                .builder()
                .answerContentType(AnswerContentType.MARKDOWN)
                .content("test")
                .node(node)
                .build();

        // when
        answerRepository.save(answer);
        Answer foundAnswer = answerRepository.findAll().getFirst();
        // then
        Assertions.assertThat(foundAnswer.getContent()).isEqualTo(answer.getContent());
    }

    @Test
    public void update() {
        // given
        Answer answer = Answer
                .builder()
                .answerContentType(AnswerContentType.MARKDOWN)
                .content("test")
                .node(node)
                .build();

        // when
        answerRepository.save(answer);
        Answer foundAnswer = answerRepository.findAll().getFirst();
        foundAnswer.setContent("updated test");
        answerRepository.save(foundAnswer);

        Answer setAnswer = answerRepository.findAll().getFirst();
        Assertions.assertThat(setAnswer.getContent()).isEqualTo(foundAnswer.getContent());
    }

    @Test
    public void delete() {
        // given
        Answer answer = Answer
                .builder()
                .answerContentType(AnswerContentType.MARKDOWN)
                .content("test")
                .node(node)
                .build();

        // when
        answerRepository.save(answer);
        Answer foundAnswer = answerRepository.findAll().getFirst();
        answerRepository.delete(foundAnswer);

        List<Answer> answerList = answerRepository.findAll();
        Assertions.assertThat(answerList.size()).isEqualTo(0);
    }
}
