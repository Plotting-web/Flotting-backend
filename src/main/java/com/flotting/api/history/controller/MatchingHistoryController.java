package com.flotting.api.history.controller;

import com.flotting.api.history.model.MatchingHistoryData;
import com.flotting.api.history.service.MatchingHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MatchingHistoryController {

    private final MatchingHistoryService matchingHistoryService;

    @GetMapping("/flotting/profile/receive/{userId}")
    public List<MatchingHistoryData> getReceivedProfile(@PathVariable(name = "userId") Long uesrId) {
        return matchingHistoryService.getAccessibleReceivedProfile(uesrId);
    }

    @GetMapping("/flotting/profile/request/{userId}")
    public List<MatchingHistoryData> getRequestedProfile(@PathVariable(name = "userId") Long uesrId) {
        return matchingHistoryService.getAccessibleRequestedProfile(uesrId);
    }
}
