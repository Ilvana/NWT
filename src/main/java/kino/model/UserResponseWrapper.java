package kino.model;

import kino.model.entities.User;

public class UserResponseWrapper {
    private Integer id;

    private String name;

    private String username;

    private String password;

    private String role;

    private boolean enabled;

    private String address;

    private String email;

    private String number;

    private boolean admin;

    private String recaptcha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getRecaptcha() {
        return recaptcha;
    }

    public void setRecaptcha(String recaptcha) {
        this.recaptcha = recaptcha;
    }

    public User user() {
        User user = new User();

        user.setAddress(getAddress());
        user.setEmail(getEmail());
        user.setNumber(getNumber());
        user.setPassword(getPassword());
        user.setAdmin(isAdmin());
        user.setName(getName());
        user.setEnable(isEnabled());
        user.setId(getId());
        user.setRole(getRole());
        user.setUsername(getUsername());

        return user;
    }
}
