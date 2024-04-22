package com.flotting.api.manager.entity;

import com.flotting.api.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

/**
 * 매니저 프로필
 */
@Entity
@Table(name = "manager_profile")
@Getter
public class ManagerProfileEntity extends BaseEntity {

    /**
     * 이름
     */
    private String name;

    /**
     * 전화번호
     */
    private String phoneNumber;

    /**
     * 비밀번호
     */
    private String password;

    /**
     * 계좌 은행
     */
    private String bankName;

    /**
     * 계좌 번호
     */
    private String bankAccount;
}
