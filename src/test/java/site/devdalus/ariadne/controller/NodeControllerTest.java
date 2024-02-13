package site.devdalus.ariadne.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import site.devdalus.ariadne.constant.NodeDirection;
import site.devdalus.ariadne.dto.NodeDto.CreateNodeDto;
import site.devdalus.ariadne.dto.NodeDto.UpdateNodeDto;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class NodeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void createTest() throws Exception {
        CreateNodeDto createNodeDto = CreateNodeDto.builder()
                .content("asdf")
                .boardId(UUID.randomUUID())
                .direction(NodeDirection.RIGHT)
                .parentId(UUID.randomUUID())
                .build();

        MvcResult result = mockMvc
                .perform(post("/v1/node")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createNodeDto)))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(201);
    }

    @Test
    void getTest() throws Exception {
        MvcResult result = mockMvc
                .perform(get("/v1/node/" + UUID.randomUUID()))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void updateTest() throws Exception {
        UpdateNodeDto updateNodeDto = UpdateNodeDto
                .builder()
                .content("asdf")
                .build();

        MvcResult result = mockMvc
                .perform(patch("/v1/node/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateNodeDto)))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void deleteTest() throws Exception {
        MvcResult result = mockMvc
                .perform(delete("/v1/node/" + UUID.randomUUID()))
                .andReturn();
        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }

    @Test
    void getNodeDetailTest() throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("/v1/node/").append(UUID.randomUUID()).append("/detail");
        MvcResult result = mockMvc
                .perform(get(sb.toString()))
                .andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);
    }
}