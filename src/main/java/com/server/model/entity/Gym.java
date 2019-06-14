package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@Table(name = "gyms")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Gym {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gym_id")
    private Integer gymId;

    @Column(name = "country", nullable = false, length = 45)
    private String country;

    @Column(name = "town", nullable = false, length = 45)
    private String town;

    @Column(name = "area", length = 45)
    private String area;

    @Column(name = "street", nullable = false, length = 45)
    private String street;

    @Column(name = "building", nullable = false)
    private int building;

    @Column(name = "office")
    private Integer office;

    @Column(name = "fine", nullable = false)
    private int fine;

    @Column(name = "post_index", nullable = false, length = 45)
    private String postIndex;

    @Column(name = "email", nullable = false, length = 45)
    private String email;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workout> workoutList;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GymPhoto> gymPhotos;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipment> equipmentList;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Timetable> timetables;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gym)) return false;
        Gym gym = (Gym) o;
        return getBuilding() == gym.getBuilding() &&
                getFine() == gym.getFine() &&
                Objects.equals(getCountry(), gym.getCountry()) &&
                Objects.equals(getTown(), gym.getTown()) &&
                Objects.equals(getArea(), gym.getArea()) &&
                Objects.equals(getStreet(), gym.getStreet()) &&
                Objects.equals(getOffice(), gym.getOffice()) &&
                Objects.equals(getPostIndex(), gym.getPostIndex()) &&
                Objects.equals(getEmail(), gym.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCountry(), getTown(), getArea(), getStreet(), getBuilding(), getOffice(), getFine(), getPostIndex(), getEmail());
    }

    @Override
    public String toString() {
        return "Gym{" +
                "gymId=" + gymId +
                ", country='" + country + '\'' +
                ", town='" + town + '\'' +
                ", area='" + area + '\'' +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", office=" + office +
                ", fine=" + fine +
                ", postIndex='" + postIndex + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
