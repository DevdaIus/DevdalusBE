package site.devdalus.ariadne.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import site.devdalus.ariadne.dto.BoardDto;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static site.devdalus.ariadne.dto.BoardDto.*;


@SpringBootTest
@AutoConfigureMockMvc
class BoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void getBoard() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/api/v1/board/" + UUID.randomUUID()))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void createBoard() throws Exception {
        CreateBoardDto createBoardDto = CreateBoardDto.builder()
                .subject("javascript")
                .build();

        MvcResult result = mockMvc
                .perform(post("/api/v1/board")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createBoardDto))
                ).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }

    @Test
    void updateBoard() throws Exception {

        UUID uuid = UUID.randomUUID();

        UpdateBoardDto updateBoardDto = UpdateBoardDto.builder()
                .subject("python")
                .boardId(uuid)
                .build();

        MvcResult result = mockMvc
                .perform(patch("/api/v1/board/" + uuid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateBoardDto))
                ).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(204);
    }

    @Test
    void deleteBoard() throws Exception {
        UUID uuid = UUID.randomUUID();

        MvcResult result = mockMvc
                .perform(delete("/api/v1/board/" + uuid))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(204);
    }
}