package kino.model.presentation;

import kino.model.entities.User;

public class UserViewModel {
    private Integer id;

    private String name;

    private String address;

    private String email;

    private String number;

    private boolean admin;

    private String username;


    private String password;

    private String role;

    private boolean enabled;

    public UserViewModel() {
    }

    public UserViewModel(User user) {
        id = user.getId();
        name = user.getName();
        address = user.getAddress();
        email = user.getEmail();
        number = user.getNumber();
        admin = user.isAdmin();
        username = user.getUsername();
        password = user.getPassword();
        role = user.getRole();
        enabled = user.getEnable();
    }

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
}
