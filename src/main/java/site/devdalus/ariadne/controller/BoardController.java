package site.devdalus.ariadne.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.devdalus.ariadne.dto.BoardDto;
import site.devdalus.ariadne.service.BoardService;

import java.util.UUID;

import static site.devdalus.ariadne.dto.BoardDto.*;

@RestController
@RequestMapping("/v1/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/{boardId}")
    public ResponseEntity<GetBoardResponseDto> getBoard(final @PathVariable UUID boardId) {
        GetBoardDto getBoardDto = new GetBoardDto(boardId);
        GetBoardResponseDto getBoardResponseDto = boardService.getBoard(getBoardDto);
        return ResponseEntity.ok(getBoardResponseDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateBoardResponseDto createBoard(final @RequestBody @Valid CreateBoardDto createBoardDto) {
        return boardService.createBoard(createBoardDto);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<Void> updateBoard(final @RequestBody @Valid UpdateBoardDto updateBoardDto, final @PathVariable UUID boardId) {

        updateBoardDto.setBoardId(boardId);
        boardService.updateBoard(updateBoardDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Void> removeBoard(@PathVariable UUID boardId) {
        
        RemoveBoardDto removeBoardDto = new RemoveBoardDto(boardId);
        boardService.removeBoard(removeBoardDto);
        return ResponseEntity.noContent().build();
    }


}
