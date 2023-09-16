package com.example.tradingapplication.model;

public enum LibraryClasses {
    Algo("com.example.tradingapplication.algo.Algo");
    public final String classPath;

    LibraryClasses(String classPath) {
        this.classPath = classPath;
    }
}
