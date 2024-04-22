package com.flotting.api.history.repository;

import com.flotting.api.history.entity.CSHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CSHistoryRepository extends JpaRepository<CSHistory, Long> {
}
