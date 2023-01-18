package com.sideproject.sideproject.social.domain;

import com.sideproject.sideproject.user.domain.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SOCIALING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Socialing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "socialing_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "social_id")
    private Social social;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}