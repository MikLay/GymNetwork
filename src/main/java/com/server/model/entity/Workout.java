package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@Table(name = "workouts")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Workout {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "workout_id")
    private Integer workoutId;

    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;

    @Column(name = "end_time", nullable = false)
    private Time endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coach_id")
    private Coach coach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Column(name = "surcharge")
    private int surcharge;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workout)) return false;
        Workout workout = (Workout) o;
        return getSurcharge() == workout.getSurcharge() &&
                Objects.equals(getStartDate(), workout.getStartDate()) &&
                Objects.equals(getEndTime(), workout.getEndTime()) &&
                Objects.equals(getCoach(), workout.getCoach()) &&
                Objects.equals(getClient(), workout.getClient()) &&
                Objects.equals(getGym(), workout.getGym());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndTime(), getCoach(), getClient(), getGym(), getSurcharge());
    }

    @Override
    public String toString() {
        return "Workout{" +
                "workoutId=" + workoutId +
                ", startDate=" + startDate +
                ", endTime=" + endTime +
                ", coach=" + coach +
                ", client=" + client +
                ", gym=" + gym +
                ", surcharge=" + surcharge +
                '}';
    }
}
