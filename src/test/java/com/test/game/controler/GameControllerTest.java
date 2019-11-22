package com.test.game.controler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.game.service.GameService;
import com.test.game.utils.CurrentMove;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class GameControllerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void createGame() throws Exception {
        CurrentMove currentMove = new CurrentMove(1L, 100L, "Test test");
        BDDMockito.given(gameService.createGame(true)).willReturn(currentMove);
        mockMvc.perform(post("/game/new-game")
                .param("playerStarts", "true")
        ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(currentMove)));
    }

    @Test
    void playerMove() throws Exception {
        CurrentMove currentMove = new CurrentMove(1L, 100L, "Test test");
        BDDMockito.given(gameService.playerMove(anyLong(), anyLong())).willReturn(currentMove);
        mockMvc.perform(post("/game/1")
                .param("nextNumber", "22")
        ).andExpect(status().isOk())
                .andExpect(content().string(mapper.writeValueAsString(currentMove)));
    }
}