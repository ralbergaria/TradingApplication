package com.example.tradeapplication.service;

import com.example.tradeapplication.algo.Algo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.mock;

class SignalServiceTest {

    private Algo algo;
    private SignalService signalService;

    @BeforeEach
    public void setUp() {
        algo = mock(Algo.class);
        signalService = new SignalService(algo);
    }
    @Test
    void shouldHandleSignal_1 () {
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(1);
        orderVerifier.verify(algo).setUp();
        orderVerifier.verify(algo).setAlgoParam(1,60);
        orderVerifier.verify(algo).performCalc();
        orderVerifier.verify(algo).submitToMarket();
    }

    @Test
    void shouldHandleSignal_2 () {
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(2);
        orderVerifier.verify(algo).reverse();
        orderVerifier.verify(algo).setAlgoParam(1,80);
        orderVerifier.verify(algo).submitToMarket();
    }

    @Test
    void shouldHandleSignal_3 () {
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(3);
        orderVerifier.verify(algo).setAlgoParam(1,90);
        orderVerifier.verify(algo).setAlgoParam(2,15);
        orderVerifier.verify(algo).submitToMarket();
    }
}
