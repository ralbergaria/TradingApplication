package com.example.tradingapplication.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SignalProcess {
    private LibraryClasses libraryClasses;
    private List<Method> methods;
}
