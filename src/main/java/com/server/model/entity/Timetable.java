package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "timetables")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer timetableId;

    @Column(name = "day", nullable = false)
    private int day;

    @Column(name = "start_time", nullable = false)
    private Time startTime;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Timetable)) return false;
        Timetable that = (Timetable) o;
        return getDay() == that.getDay() &&
                Objects.equals(getStartTime(), that.getStartTime()) &&
                Objects.equals(getEndTime(), that.getEndTime()) &&
                Objects.equals(getCoach(), that.getCoach()) &&
                Objects.equals(getGym(), that.getGym());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDay(), getStartTime(), getEndTime(), getCoach(), getGym());
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableId=" + timetableId +
                ", day=" + day +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", coach=" + coach +
                ", gym=" + gym +
                '}';
    }
}
