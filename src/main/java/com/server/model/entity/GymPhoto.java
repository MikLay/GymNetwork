package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "gym_photos")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class GymPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer photoId;

    @Column(name = "photo_url", nullable = false, length = 250)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GymPhoto)) return false;
        GymPhoto gymPhoto = (GymPhoto) o;
        return Objects.equals(getPhotoUrl(), gymPhoto.getPhotoUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhotoUrl());
    }

    @Override
    public String toString() {
        return "GymPhoto{" +
                "photoId=" + photoId +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
