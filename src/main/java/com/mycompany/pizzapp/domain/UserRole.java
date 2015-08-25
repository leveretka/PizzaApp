package com.mycompany.pizzapp.domain;

import javax.persistence.*;

/**
 * Created by margarita on 22.08.15.
 */

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_role_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "authority")
    private String role;

    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public UserRole() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
