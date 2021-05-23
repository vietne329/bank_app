package com.bank_management.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "username"), @UniqueConstraint(columnNames = "email")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="full_name", nullable = false)
    private String fullName;

    @Column(name="address" )
    private String address;

    @Column(name = "money")
    private Double money;

    @Column(name="dob")
    private String dob;

    @Column(name="phone")
    private String phone;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="id_card")
    private String idCard;

    @Column(name="facebook_id")
    private String facebookId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String username, String password, String fullName, String address, String dob, String phone, String email, String idCard, String facebookId) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
        this.facebookId = facebookId;
    }

    public User(String username, String password, String fullName, String address, String dob, String phone, String email,Double money, String idCard, String facebookId) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.dob = dob;
        this.phone = phone;
        this.money = money;
        this.email = email;
        this.idCard = idCard;
        this.facebookId = facebookId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
}
