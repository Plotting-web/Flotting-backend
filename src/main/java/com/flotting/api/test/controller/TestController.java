package com.flotting.api.test.controller;

import com.flotting.api.test.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @GetMapping
    public String testList(){
        return testService.testList();
    }
}

