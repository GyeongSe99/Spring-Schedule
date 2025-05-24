package com.sparta.schedule.schedule;

import com.sparta.schedule.common.BaseEntity;
import com.sparta.schedule.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
