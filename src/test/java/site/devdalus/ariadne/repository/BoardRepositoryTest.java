package site.devdalus.ariadne.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.ActiveProfiles;
import site.devdalus.ariadne.domain.Board;

import java.util.List;

@DataJpaTest
@EnableJpaAuditing
@ActiveProfiles("test")
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void save() {
        //given
        Board board = new Board("Javascript");

        //when
        boardRepository.save(board);
        Board foundBoard = boardRepository.findAll().getFirst();

        //then
        Assertions.assertThat(board.getSubject()).isEqualTo(foundBoard.getSubject());
    }


    @Test
    public void delete() {
        Board board = new Board("Javascript");

        boardRepository.save(board);
        Board foundBoard = boardRepository.findAll().getFirst();

        boardRepository.delete(foundBoard);
        List<Board> boards = boardRepository.findAll();

        Assertions.assertThat(boards.size()).isEqualTo(0);
    }
}
