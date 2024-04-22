package com.flotting.api.user.entity;

import com.flotting.api.manager.entity.ManagerProfileEntity;
import com.flotting.api.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 매니저 전담 소개 신청자 관리 테이블
 */
@Entity
@Getter
public class PersonalManagerRequesterEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private ManagerProfileEntity manager;

    @OneToOne
    @JoinColumn(name = "requester_id")
    private UserSimpleEntity requester;

    @Column(length = 400)
    private String managerLetter;

    @Column(length = 400)
    private String requesterLetter;
}
