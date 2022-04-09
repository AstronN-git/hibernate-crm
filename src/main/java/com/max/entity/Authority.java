package com.max.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
@Getter
@Setter
public class Authority {
    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "login", referencedColumnName = "login")
    private User user;

    @Column(name = "authority")
    private String authority;

    @Override
    public String toString() {
        return "Authority{" +
                "user=" + user.getLogin() +
                ", authority='" + authority + '\'' +
                '}';
    }
}
