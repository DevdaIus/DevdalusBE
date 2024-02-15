package site.devdalus.ariadne.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.Remove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.dto.BoardDto;
import site.devdalus.ariadne.exception.ResourceNotExistException;
import site.devdalus.ariadne.repository.BoardRepository;

import java.util.Optional;
import java.util.UUID;

import static site.devdalus.ariadne.dto.BoardDto.*;

@Service
@AllArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public GetBoardResponseDto getBoard(GetBoardDto getBoardDto) {
        //TODO : 컨트롤러단 ExceptionHandler
        Board board = boardRepository
                .findById(getBoardDto.getBoardId())
                .orElseThrow(ResourceNotExistException::new);
        return GetBoardResponseDto.toDto(board);
    }

    @Transactional
    public CreateBoardResponseDto createBoard(CreateBoardDto createBoardDto) {
        String subject = createBoardDto.getSubject();
        Board newBoard = new Board(subject);
        Board boardEntity = boardRepository.save(newBoard);

        return CreateBoardResponseDto.toDto(boardEntity);
    }


    @Transactional
    public void updateBoard(UpdateBoardDto updateBoardDto) {
        Board board = boardRepository
                .findById(updateBoardDto.getBoardId())
                .orElseThrow(ResourceNotExistException::new);

        board.setSubject(updateBoardDto.getSubject());
    }

    @Transactional
    public void removeBoard(RemoveBoardDto removeBoardDto) {
        //TODO: soft delete, but real delete for now
        boardRepository.deleteById(removeBoardDto.getBoardId());
    }

}
