package com.companies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Table(name = "company")
@Entity
public class Company {
    @Id
    private UUID id;
    private String name;
    private Integer size;
    private String address;

    public Company() {}

    public Company(UUID id, String name, Integer size, String address) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}