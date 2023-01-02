package com.sideproject.sideproject.customer.domain;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "USERS", uniqueConstraints =
        {@UniqueConstraint(name = "EMAIL_NICKNAME_UNIQUE", columnNames = {"email","nickname"})})
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType; // 기본 회원, 카카오 회원 구분

    @Column(name = "sns_id")
    private Long snsId; //sns 계정 회원가입 유무 확인용

    @NotNull
    private String email; // 아이디

    private String password; //카카오 유저는 비밀번호가 없음

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "nickname")
    private String nickname;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull
    @Column(name = "dong_code")
    private Long dongCode;
    @NotNull
    @Column(name = "dong_name")
    private String dongName;

    private String profile;
    private String bio;//한마디 소개글

    @Column(length = 1000, name = "refresh_token")
    private String refreshToken; //JWT

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_status")
    private UserStatus userStatus;
}
