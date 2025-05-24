package com.sparta.schedule.schedule.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.schedule.schedule.entity.QSchedule;
import com.sparta.schedule.schedule.entity.Schedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Schedule> findSchedulesByUpdatedAtAndWriterId(LocalDate updatedAt, Long writerId) {
        QSchedule schedule = QSchedule.schedule;

        BooleanBuilder builder = new BooleanBuilder();

        if (updatedAt != null) {
            builder.and(schedule.updatedAt.eq(updatedAt));
        }

        if (writerId != null) {
            builder.and(schedule.user.id.eq(writerId));
        }

        return queryFactory.selectFrom(schedule)
                .where(builder)
                .fetch();
    }
}
