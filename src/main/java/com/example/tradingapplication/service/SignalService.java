package com.example.tradingapplication.service;

import com.example.tradingapplication.algo.Algo;
import com.example.tradingapplication.algo.SignalHandler;
import com.example.tradingapplication.exception.ProcessSignalException;
import com.example.tradingapplication.model.Signal;
import com.example.tradingapplication.repository.SignalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.apache.commons.collections4.CollectionUtils.isNotEmpty;


@Service
@RequiredArgsConstructor
public class SignalService implements SignalHandler {
    private final Algo algo;
    private final SignalRepository signalRepository;

    @Override
    public void handleSignal(int signalId) {
        Optional<Signal> signalOptional = signalRepository.findById(signalId);
        if (signalOptional.isPresent()) {
            processSignal(signalOptional.get());
        } else {
            algo.cancelTrades();
        }
        algo.doAlgo();
    }

    @Transactional
    public void createSignals(List<Signal> signals) {
        signalRepository.saveAll(signals);
    }

    private void processSignal(Signal signal) {
        signal.getMethods().stream().sorted(Comparator.comparing(com.example.tradingapplication.model.Method::getOrder)).forEach(method -> {
            try {
                if (isNotEmpty(method.getParams())) {
                    processWithParams(method);
                } else {
                    processWithoutParams(method);
                }
            } catch (NoSuchMethodException e) {
                throw new ProcessSignalException("Method not found. signalId " + signal.getId() + " method " + method.getName());
            } catch (InvocationTargetException | IllegalAccessException e) {
                throw new ProcessSignalException(" Was not possible to invoke the method " + method.getName() + " signalId " + signal.getId());
            }
        });
    }

    private void processWithoutParams(com.example.tradingapplication.model.Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method methodClass = algo.getClass().getMethod(method.getName());
        methodClass.invoke(algo);
    }

    private void processWithParams(com.example.tradingapplication.model.Method method) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] list = method.getParams().stream().map(value -> Integer.TYPE).toArray(Class<?>[]::new);
        Method methodClass = algo.getClass().getMethod(method.getName(),list);
        methodClass.invoke(algo, method.getParams().toArray());
    }
}
