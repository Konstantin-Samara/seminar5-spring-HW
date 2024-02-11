package com.example.seminar5spring;

import jakarta.persistence.*;

@Entity
public class Command {


    enum Status {Running, Completed, Disabled}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    private Status status = Status.Disabled;

    @Column(nullable = true)
    private String runningDate = "no Date";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getRunningDate() {
        return runningDate;
    }

    public void setRunningDate(String runningDate) {
        this.runningDate = runningDate;
    }

    @Override
    public String toString(){
        return this.id+" "+this.description+" "+this.status+" "+this.runningDate;
    }

}
