package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "subscriptions")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Integer subscriptionId;

    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    private Date endDate;

    @Column(name = "workout_start_time", nullable = false)
    private Time workoutStartTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "workout_end_time", nullable = false)
    private Time workoutEndTime;

    @Column(name = "price", nullable = false)
    private int price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;
        Subscription that = (Subscription) o;
        return getPrice() == that.getPrice() &&
                Objects.equals(getStartDate(), that.getStartDate()) &&
                Objects.equals(getEndDate(), that.getEndDate()) &&
                Objects.equals(getWorkoutStartTime(), that.getWorkoutStartTime()) &&
                Objects.equals(getClient(), that.getClient()) &&
                Objects.equals(getWorkoutEndTime(), that.getWorkoutEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStartDate(), getEndDate(), getWorkoutStartTime(), getClient(), getWorkoutEndTime(), getPrice());
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + subscriptionId +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", workoutStartTime=" + workoutStartTime +
                ", client=" + client +
                ", workoutEndTime=" + workoutEndTime +
                ", price=" + price +
                '}';
    }
}
