package com.patsnap.learn.com.patsnap.learn.bean;

public class Person {
    private Integer id;
    private String name;
    private String address;

    public Person(Integer id) {
        this.id = id;
    }

    public Person(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    @Override
    public String toString() {
        return "id:" + id + ", name:" + name + ", address:" + address;
    }
}
