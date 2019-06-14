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
public class GymPhotos {
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
        if (!(o instanceof GymPhotos)) return false;
        GymPhotos gymPhotos = (GymPhotos) o;
        return Objects.equals(getPhotoUrl(), gymPhotos.getPhotoUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPhotoUrl());
    }

    @Override
    public String toString() {
        return "GymPhotos{" +
                "photoId=" + photoId +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
