package com.flotting.api.history.service;

import com.flotting.api.history.entity.CSHistory;
import com.flotting.api.history.model.CSHistoryResponseDto;
import com.flotting.api.history.repository.CSHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CSHistoryService {

    private final CSHistoryRepository csHistoryRepository;

    @Transactional(readOnly = true)
    public CSHistoryResponseDto getCSHistory(Long id) {
        CSHistory result = csHistoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException());
        CSHistoryResponseDto csHistoryResponseDto = new CSHistoryResponseDto(result);

        return csHistoryResponseDto;
    }

    @Transactional(readOnly = true)
    public List<CSHistoryResponseDto> getAllCSHistory(Pageable pageable) {
        ArrayList<CSHistoryResponseDto> result = new ArrayList<>();
        List<CSHistory> datas = csHistoryRepository.findAll(pageable).getContent();
        datas.forEach(data -> {
            result.add(new CSHistoryResponseDto(data));
        });

        return result;
    }

}
