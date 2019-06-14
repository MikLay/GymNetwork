package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @OneToMany(mappedBy = "userType", cascade = CascadeType.ALL)
    private List<User> roleType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles)) return false;
        Roles roles = (Roles) o;
        return Objects.equals(getRoleType(), roles.getRoleType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleType());
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleId=" + roleId +
                ", roleType='" + roleType + '\'' +
                '}';
    }
}
