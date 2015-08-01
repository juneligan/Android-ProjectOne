package com.project_one.model;

/**
 * Created by JenuNagil on 7/28/2015.
 */
public class User {

    private Long id;
    private Role role;
    private String username;
    private String password;

    public User() {
        role = new Role();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }
}
