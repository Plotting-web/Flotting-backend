package com.flotting.api.test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String testList(){
        return "test";
    }
}
