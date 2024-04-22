package com.flotting.api.user.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Getter
@Table(name="USER_PROFILE")
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UserProfileEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    private String userName;

    private String userStatus;

    private Long phoneNumber;

    private String email;

    private Long age;

    private String job;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
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

    public static UserProfileEntity signUserBuilder(UserSigninRequestVo userSigninRequestVo){
        return UserProfileEntity.builder()
                .userName(userSigninRequestVo.getUserName())
                .email(userSigninRequestVo.getEmail())
                .phoneNumber(userSigninRequestVo.getPhoneNumber())
                .build();
    }
}
