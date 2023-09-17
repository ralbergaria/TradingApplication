package com.example.tradingapplication.controller;

import com.example.tradingapplication.service.SignalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SignalControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SignalService service;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    @Test
    void shouldSignalProcessOk() throws Exception {
        doNothing().when(service).handleSignal(1);
        mockMvc.perform(post("/api/v1/signal/process").content("1").contentType(APPLICATION_JSON).header("X-API-Key", "test"))
                .andExpect(status().isOk());

    }

    @Test
    void shouldSignalProcessForbidden() throws Exception {
        doNothing().when(service).handleSignal(1);
        mockMvc.perform(post("/api/v1/signal/process").content("1").contentType(APPLICATION_JSON).header("X-API-Key", "test-forbidden"))
                .andExpect(status().isForbidden());

    }
}
