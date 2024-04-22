package com.flotting.api.manager.repository;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<ManagerProfileEntity, Long> {
}
