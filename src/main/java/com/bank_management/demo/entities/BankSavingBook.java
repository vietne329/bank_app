package com.bank_management.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "banksavingbooks")
public class BankSavingBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="money", nullable = false)
    private double money;

    @Column(name="branch", nullable = false)
    private String branch;

    @Column(name="start_date", nullable = false)
    private String startDate;

    @ManyToOne
    @JoinColumn(name = "interest_id")
    private Interestrate interestrate;

    @OneToOne
    @JoinColumn(name = "users_id")
    private User user;

    public Interestrate getInterestrate() {
        return interestrate;
    }

    public void setInterestrate(Interestrate interestrate) {
        this.interestrate = interestrate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
