package com.example.tradingapplication.controller;

import com.example.tradingapplication.service.SignalService;
import com.example.tradingapplication.util.TestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SignalControllerTest {

    private final String SIGNAL_DTO_JSON_PATH = "json/signalDTO.json";
    private final String SIGNAL_DTO_Without_id_JSON_PATH = "json/signalDTO_without_id.json";
    private final String SIGNAL_DTO_Without_param_JSON_PATH = "json/signalDTO_method_without_param.json";
    private final TestHelper testHelper = new TestHelper();
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
    void shouldSignalProcess_return_Ok() throws Exception {
        doNothing().when(service).handleSignal(1);
        mockMvc.perform(post("/api/v1/signal/process").content("1").contentType(APPLICATION_JSON).header("X-API-Key", "test"))
                .andExpect(status().isOk());

    }

    @Test
    void shouldSignalProcess_return_Forbidden() throws Exception {
        doNothing().when(service).handleSignal(1);
        mockMvc.perform(post("/api/v1/signal/process").content("1").contentType(APPLICATION_JSON).header("X-API-Key", "test-forbidden"))
                .andExpect(status().isForbidden());

    }

    @Test
    void shouldCreateSignal_return_Ok() throws Exception {
        doNothing().when(service).createSignals(any());
        mockMvc.perform(post("/api/v1/signal").content(testHelper.fromFile(SIGNAL_DTO_JSON_PATH)).contentType(APPLICATION_JSON).header("X-API-Key", "test"))
                .andExpect(status().isOk());

    }

    @Test
    void shouldCreateSignal_return_Forbidden() throws Exception {
        doNothing().when(service).createSignals(any());
        mockMvc.perform(post("/api/v1/signal").content(testHelper.fromFile(SIGNAL_DTO_JSON_PATH)).contentType(APPLICATION_JSON).header("X-API-Key", "test-forbidden"))
                .andExpect(status().isForbidden());

    }

    @Test
    void shouldCreateSignal_Without_id_return_Bad_Request() throws Exception {
        doNothing().when(service).createSignals(any());
        mockMvc.perform(post("/api/v1/signal").content(testHelper.fromFile(SIGNAL_DTO_Without_id_JSON_PATH)).contentType(APPLICATION_JSON).header("X-API-Key", "test"))
                .andExpect(status().isBadRequest());

    }

    @Test
    void shouldCreateSignal_Without_param_Return_OK() throws Exception {
        doNothing().when(service).createSignals(any());
        mockMvc.perform(post("/api/v1/signal").content(testHelper.fromFile(SIGNAL_DTO_Without_param_JSON_PATH)).contentType(APPLICATION_JSON).header("X-API-Key", "test"))
                .andExpect(status().isOk());

    }
}
