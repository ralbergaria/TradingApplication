package com.example.tradingapplication.service;

import com.example.tradingapplication.algo.Algo;
import com.example.tradingapplication.exception.ProcessSignalException;
import com.example.tradingapplication.model.Method;
import com.example.tradingapplication.model.Signal;
import com.example.tradingapplication.repository.SignalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignalServiceTest {

    private Algo algo;
    private SignalService signalService;
    private SignalRepository signalRepository;

    @BeforeEach
    public void setUp() {
        algo = mock(Algo.class);
        signalRepository = mock(SignalRepository.class);
        signalService = new SignalService(algo, signalRepository);
    }
    @Test
    void shouldHandleSignal_1 () {
        Method methodSetup = Method.builder().order(0).name("setUp").build();
        Method methodSetAlgoParam = Method.builder().order(1).name("setAlgoParam").params(List.of(1,60)).build();
        Method methodPerformCalc = Method.builder().order(2).name("performCalc").build();
        Method methodSubmitToMarket = Method.builder().order(3).name("submitToMarket").build();
        Signal signal = Signal.builder().id(1)
                .methods(List.of(methodSetup, methodSetAlgoParam, methodPerformCalc, methodSubmitToMarket))
                .build();
        when(signalRepository.findById(any())).thenReturn(Optional.of(signal));
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(1);
        orderVerifier.verify(algo).setUp();
        orderVerifier.verify(algo).setAlgoParam(1,60);
        orderVerifier.verify(algo).performCalc();
        orderVerifier.verify(algo).submitToMarket();
        orderVerifier.verify(algo).doAlgo();
    }

    @Test
    void shouldHandleSignal_2 () {
        Method methodReverse = Method.builder().order(0).name("reverse").build();
        Method methodSetAlgoParam = Method.builder().order(1).name("setAlgoParam").params(List.of(1,80)).build();
        Method methodSubmitToMarket = Method.builder().order(2).name("submitToMarket").build();
        Signal signal = Signal.builder().id(2)
                .methods(List.of(methodReverse, methodSetAlgoParam, methodSubmitToMarket))
                .build();
        when(signalRepository.findById(any())).thenReturn(Optional.of(signal));
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(2);
        orderVerifier.verify(algo).reverse();
        orderVerifier.verify(algo).setAlgoParam(1,80);
        orderVerifier.verify(algo).submitToMarket();
        orderVerifier.verify(algo).doAlgo();
    }

    @Test
    void shouldHandleSignal_3 () {
        Method methodSetAlgoParam1 = Method.builder().order(0).name("setAlgoParam").params(List.of(1,90)).build();
        Method methodSetAlgoParam2 = Method.builder().order(1).name("setAlgoParam").params(List.of(2,15)).build();
        Method methodSubmitToMarket = Method.builder().order(2).name("submitToMarket").build();
        Signal signal = Signal.builder().id(3)
                .methods(List.of(methodSetAlgoParam1, methodSetAlgoParam2, methodSubmitToMarket))
                .build();
        when(signalRepository.findById(any())).thenReturn(Optional.of(signal));
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(3);
        orderVerifier.verify(algo).setAlgoParam(1,90);
        orderVerifier.verify(algo).setAlgoParam(2,15);
        orderVerifier.verify(algo).submitToMarket();
        orderVerifier.verify(algo).doAlgo();
    }

    @Test
    void shouldThrowProcessSignalException(){
        Method methodNoExist = Method.builder().order(0).name("noExistMethod").build();
        Signal signal = Signal.builder().id(4)
                .methods(List.of(methodNoExist))
                .build();
        when(signalRepository.findById(any())).thenReturn(Optional.of(signal));
        assertThrows(ProcessSignalException.class, () -> signalService.handleSignal(4));
    }

    @Test
    void shouldRunWhenTheSignalNotExists(){
        when(signalRepository.findById(any())).thenReturn(Optional.empty());
        InOrder orderVerifier = Mockito.inOrder(algo);
        signalService.handleSignal(5);
        orderVerifier.verify(algo).cancelTrades();
        orderVerifier.verify(algo).doAlgo();
    }
}