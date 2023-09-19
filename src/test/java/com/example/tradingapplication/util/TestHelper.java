package com.example.tradingapplication.util;


import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;

public class TestHelper {
    public String fromFile(String path) throws IOException {
        return StreamUtils.copyToString(
                new ClassPathResource(path).getInputStream(), defaultCharset());
    }
}
