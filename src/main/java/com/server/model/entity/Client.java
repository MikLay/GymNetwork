package com.server.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Builder(toBuilder = true)
@Table(name = "clients")
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PACKAGE)
@Getter
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;


    @Basic
    @Column(name = "firstname", nullable = false, length = 45)
    private String firstname;


    @Column(name = "middlename", nullable = true, length = 45)
    private String middlename;

    @Column(name = "photo_url", nullable = false, length = 750)
    private String photoUrl;


    @Column(name = "email", nullable = true, length = 250)
    private String email;

    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Workout> workoutList;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subscription> subscriptions;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return clientId == client.clientId &&
                Objects.equals(lastname, client.lastname) &&
                Objects.equals(firstname, client.firstname) &&
                Objects.equals(middlename, client.middlename) &&
                Objects.equals(photoUrl, client.photoUrl) &&
                Objects.equals(email, client.email) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(birthDate, client.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, lastname, firstname, middlename, photoUrl, email, phone, birthDate);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
