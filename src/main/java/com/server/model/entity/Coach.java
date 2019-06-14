package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@Table(name = "coaches")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "coach_id")
    private Integer coachId;

    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;

    @Column(name = "firstname", nullable = false, length = 45)
    private String firstname;

    @Column(name = "middlename", length = 45)
    private String middlename;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @Column(name = "photo_url", nullable = false, length = 250)
    private String photoUrl;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @Column(name = "town", nullable = false, length = 45)
    private String town;

    @Column(name = "area", length = 45)
    private String area;

    @Column(name = "street", nullable = false, length = 45)
    private String street;

    @Column(name = "building", nullable = false, length = 45)
    private String building;

    @Column(name = "flat")
    private Integer flat;

    @Column(name = "sport_rang", length = 45)
    private String sportRang;

    @Column(name = "payment", nullable = false)
    private int payment;

    @Column(name = "sex", nullable = false)
    private byte sex;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL)
    private List<Workout> workouts;

    @OneToMany(mappedBy = "coach", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timetable> timetables;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coach)) return false;
        Coach coach = (Coach) o;
        return getPayment() == coach.getPayment() &&
                getSex() == coach.getSex() &&
                Objects.equals(getLastname(), coach.getLastname()) &&
                Objects.equals(getFirstname(), coach.getFirstname()) &&
                Objects.equals(getMiddlename(), coach.getMiddlename()) &&
                Objects.equals(getEmail(), coach.getEmail()) &&
                Objects.equals(getPhone(), coach.getPhone()) &&
                Objects.equals(getSportRang(), coach.getSportRang());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLastname(), getFirstname(), getMiddlename(), getEmail(), getPhone(), getSportRang(), getPayment(), getSex());
    }

    @Override
    public String toString() {
        return "Coach{" +
                "coachId=" + coachId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", flat=" + flat +
                ", sportRang='" + sportRang + '\'' +
                ", payment=" + payment +
                ", sex=" + sex +
                '}';
    }
}
