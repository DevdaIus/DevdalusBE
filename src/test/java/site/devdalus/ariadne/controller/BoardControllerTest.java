package site.devdalus.ariadne.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import site.devdalus.ariadne.domain.Board;
import site.devdalus.ariadne.exception.ResourceNotExistException;
import site.devdalus.ariadne.repository.BoardRepository;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static site.devdalus.ariadne.dto.BoardDto.*;


@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BoardRepository boardRepository;


    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getBoard() throws Exception {

        String subject = "javascript";
        Board board = boardRepository.save(new Board(subject));

        MvcResult result = mockMvc
                .perform(get("/v1/board/" + board.getBoardId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.subject").value(subject))
                .andReturn();


    }

    @Test
    void createBoard() throws Exception {
        CreateBoardDto createBoardDto = CreateBoardDto.builder()
                .subject("javascript")
                .build();

        MvcResult result = mockMvc
                .perform(post("/v1/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBoardDto))
                ).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }

    @Test
    void updateBoard() throws Exception {

        String subject = "javascript";
        String newSubject = "python";
        Board board = boardRepository.save(new Board(subject));
        UpdateBoardDto updateBoardDto = new UpdateBoardDto(board.getBoardId(), newSubject);

        MvcResult result = mockMvc
                .perform(patch("/v1/board/" + board.getBoardId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateBoardDto))
                )
                .andExpect(status().isNoContent())
                .andReturn();

        Board updatedBoard = boardRepository
                .findById(board.getBoardId())
                .orElseThrow(ResourceNotExistException::new);

        assertThat(updatedBoard.getSubject()).isEqualTo(newSubject);

    }

    @Test
    void removeBoard() throws Exception {
        UUID uuid = UUID.randomUUID();

        MvcResult result = mockMvc
                .perform(delete("/v1/board/" + uuid))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(204);


    }
}