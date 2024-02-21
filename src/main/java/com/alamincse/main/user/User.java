package com.alamincse.main.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id ;

    @Column(nullable = false, length = 45)
    private String email;

    @Column(nullable = false, length = 15)
    private String password ;

    @Column(nullable = false, length = 45 , name = "first_name")
    private String firstName;

    @Column(nullable = false, length = 45 , name = "last_name")
    private String lastName;



    private boolean enabled;

    public User() {

    }

    public User(int id, String email, String password, String firstName, String lastName, boolean enabled) {
        Id = id;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
