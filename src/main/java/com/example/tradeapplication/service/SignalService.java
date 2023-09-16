package com.example.tradeapplication.service;

import com.example.tradeapplication.algo.Algo;
import com.example.tradeapplication.algo.SignalHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignalService implements SignalHandler {
    private final Algo algo;
    @Override
    public void handleSignal(int signal) {
        switch (signal) {
            case 1 -> {
                algo.setUp();
                algo.setAlgoParam(1, 60);
                algo.performCalc();
                algo.submitToMarket();
            }
            case 2 -> {
                algo.reverse();
                algo.setAlgoParam(1, 80);
                algo.submitToMarket();
            }
            case 3 -> {
                algo.setAlgoParam(1, 90);
                algo.setAlgoParam(2, 15);
                algo.performCalc();
                algo.submitToMarket();
            }
            default -> algo.cancelTrades();
        }
        algo.doAlgo();
    }
}
