package com.flotting.api.history.entity;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import com.flotting.api.user.entity.UserSimpleEntity;
import com.flotting.api.user.enums.CSEnum;
import com.flotting.api.user.enums.ProcessStatus;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

/**
 * cs 이력 관리 테이블
 */
@Entity
@Getter
@Table(name = "cs_history",
        indexes = @Index(name = "statusIndex", columnList = "status"))
public class CSHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 접수일
     */
    private LocalDate requestDate;

    /**
     * 처리 매니저
     */
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerProfileEntity manager;

    /**
     * 신청자
     */
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private UserSimpleEntity requester;

    /**
     * 처리현황
     */
    private ProcessStatus status;

    /**
     * 요청내역
     */
    private String content;

    /**
     * 상담 유형
     */
    private CSEnum csType;

    /**
     * 매니저 의견
     */
    private String managerComment;
}
