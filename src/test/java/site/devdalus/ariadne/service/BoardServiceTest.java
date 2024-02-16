package site.devdalus.ariadne.service;

import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.dto.BoardDto;
import site.devdalus.ariadne.exception.ResourceNotExistException;
import site.devdalus.ariadne.repository.BoardRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static site.devdalus.ariadne.dto.BoardDto.*;


@SpringBootTest
@Transactional
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;


    @Test
    void getBoard() {
        Board board = boardRepository.save(new Board("javascript"));

        GetBoardDto getBoardDto = new GetBoardDto(board.getBoardId());
        GetBoardResponseDto getBoardResponseDto = boardService.getBoard(getBoardDto);

        assertThat(board.getRootNodeId()).isEqualTo(getBoardResponseDto.getRootNodeId());
    }


    @Test
    void createBoard() {
        CreateBoardDto createBoardDto = new CreateBoardDto("javascript");
        CreateBoardResponseDto createBoardResponseDto = boardService.createBoard(createBoardDto);

        Board board = boardRepository
                .findById(createBoardResponseDto.getBoardId())
                .orElseThrow(ResourceNotExistException::new);

        assertThat(board.getSubject()).isEqualTo("javascript");
    }

    @Test
    void updateBoard() {
        Board board = boardRepository.save(new Board("javascript"));

        UpdateBoardDto updateBoardDto = new UpdateBoardDto(board.getBoardId(), "python");
        boardService.updateBoard(updateBoardDto);

        Board foundBoard = boardRepository
                .findById(board.getBoardId())
                .orElseThrow(ResourceNotExistException::new);

        assertThat(foundBoard.getSubject()).isEqualTo("python");
    }

    @Test
    void removeBoard() {
        Board board = boardRepository.save(new Board("javascript"));

        RemoveBoardDto removeBoardDto = new RemoveBoardDto(board.getBoardId());
        boardService.removeBoard(removeBoardDto);

        Optional<Board> foundBoard = boardRepository.findById(board.getBoardId());
        assertTrue(foundBoard.isEmpty());
    }
}