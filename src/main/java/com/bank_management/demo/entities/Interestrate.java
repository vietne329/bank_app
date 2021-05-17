package com.bank_management.demo.entities;





import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "interestrate")
public class Interestrate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="type_of_saving", nullable = false)
    private String typeOfSaving;

    @Column(name="times", nullable = false)
    private int times;

    @Column(name="interest_rate", nullable = false)
    private double interestRate;

    @OneToMany(mappedBy = "interestrate", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BankSavingBook> bankSavingBookSet = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfSaving() {
        return typeOfSaving;
    }

    public void setTypeOfSaving(String typeOfSaving) {
        this.typeOfSaving = typeOfSaving;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
