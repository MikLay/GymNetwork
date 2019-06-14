package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer equipmentId;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "type", nullable = false, length = 45)
    private String type;

    @Column(name = "condition", nullable = false, length = 45)
    private String condition;

    @Column(name = "photo_url", nullable = false, length = 250)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gym_id")
    private Gym gym;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        Equipment equipment = (Equipment) o;
        return Objects.equals(getName(), equipment.getName()) &&
                Objects.equals(getType(), equipment.getType()) &&
                Objects.equals(getCondition(), equipment.getCondition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getType(), getCondition());
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "equipmentId=" + equipmentId +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", condition='" + condition + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }
}
