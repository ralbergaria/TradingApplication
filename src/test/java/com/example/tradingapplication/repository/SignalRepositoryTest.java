package com.example.tradingapplication.repository;

import com.example.tradingapplication.model.Method;
import com.example.tradingapplication.model.Signal;
import com.example.tradingapplication.model.SignalProcess;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static com.example.tradingapplication.model.LibraryClasses.Algo;

@DataMongoTest
class SignalRepositoryTest {
    @Autowired
    private SignalRepository signalRepository;

    @Test
    void shouldSaveTheSignal(){
        Signal signal = Signal.builder().id(1)
                .signalProcess(
                        SignalProcess.builder()
                                .libraryClasses(Algo)
                                .methods(List.of(Method.builder().name("doAlgo")
                                        .params(List.of(1, 2)).build()))
                        .build()).build();
        signalRepository.save(signal);
        Assertions.assertFalse(signalRepository.findAll().isEmpty());
    }
}
