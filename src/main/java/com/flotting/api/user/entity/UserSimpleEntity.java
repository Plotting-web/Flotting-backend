package com.flotting.api.user.entity;

import com.flotting.api.user.enums.GenderEnum;
import com.flotting.api.user.enums.UserStatusEnum;
import com.flotting.api.user.model.UserSimpleRequestDto;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * 1차 프로필 테이블
 */
@Entity
@Table(name = "user_simple_profile")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
public class UserSimpleEntity implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userNo;

    /**
     * 생성일
     */
    private LocalDateTime createdAt;

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    /**
     * 이름
     */
    private String name;

    /**
     * 패스워드(pass CI를 인코딩 한 값)
     */
    private String password;

    /**
     * 나이
     */
    private Integer age;

    /**
     * 전화번호
     */
    @Column
    private String phoneNumber;

    /**
     * 2차 프로필 id
     */
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_detail_entity")
    private UserDetailEntity userDetailEntity;

    /**
     * 이메일
     */
    @Column(unique = true)
    private String email;

    public UserSimpleEntity(UserSimpleRequestDto requestDto , String encodedPassword) {
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.password = encodedPassword;
        this.phoneNumber = requestDto.getPhoneNumber();
    }

    public void setDetailUser(UserDetailEntity detailProfile) {
        this.userDetailEntity = detailProfile;
    }

    public UserSimpleEntity updateInfo(UserSimpleRequestDto requestDto) {
        this.name = requestDto.getName();
        this.age = requestDto.getAge();
        this.phoneNumber = requestDto.getPhoneNumber();
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public int getAgeScore(GenderEnum gender) {
        if (GenderEnum.M.equals(gender)) {
            if (this.age >= 40) {
                return 2;
            } else {
                return 0;
            }
        } else {
            if (this.age >= 36) {
                return 0;
            } else if (this.age >= 30) {
                return 5;
            } else if (this.age >= 25) {
                return 10;
            } else if (this.age >= 20) {
                return 15;
            } else {
                return 0;
            }
        }
    }
}
