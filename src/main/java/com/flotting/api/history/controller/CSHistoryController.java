package com.flotting.api.history.controller;

import com.flotting.api.history.model.CSHistoryResponseDto;
import com.flotting.api.history.service.CSHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CSHistoryController {

    private final CSHistoryService csHistoryService;

    @GetMapping("/cs-history/all")
    public List<CSHistoryResponseDto> getAllHistories(Pageable pageable) {
        return csHistoryService.getAllCSHistory(pageable);
    }

    @GetMapping("/cs-history/{historyId}")
    public CSHistoryResponseDto getHistory(@PathVariable(name = "historyId") Long historyId) {
        return csHistoryService.getCSHistory(historyId);
    }
}
