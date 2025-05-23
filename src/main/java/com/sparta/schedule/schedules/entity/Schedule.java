package com.sparta.schedule.schedules.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Schedule {

    @Id()
    private Long id;
}
