package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_email", nullable = false, length = 250)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 250)
    private String userPassword;

    @Column(name = "user_salt", nullable = false, length = 250)
    private String userSalt;

    @Column(name = "user_type", nullable = false, length = 50)
    private String userType;

    @Column(name = "user_real_id", nullable = false, length = 50)
    private Integer user_real_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUserEmail(), user.getUserEmail()) &&
                Objects.equals(getUserPassword(), user.getUserPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEmail(), getUserPassword());
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userEmail='" + userEmail + "}";
    }
}
