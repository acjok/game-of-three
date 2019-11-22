package com.test.game.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MoveServiceTest {

    @InjectMocks
    private MoveService moveService;

    @Test
    void getComputersNumber() {
        assertEquals(5, moveService.getComputersNumber(14L));
        assertEquals(5, moveService.getComputersNumber(15L));
        assertEquals(5, moveService.getComputersNumber(16L));
        assertNotEquals(5, moveService.getComputersNumber(17L));
    }
}