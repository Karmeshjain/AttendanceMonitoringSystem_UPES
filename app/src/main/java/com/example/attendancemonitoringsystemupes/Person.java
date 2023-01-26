package com.example.attendancemonitoringsystemupes;

public class Person {
    private String name;
    private int age;
    private String gender;
    private String address;
// to store details
    public Person(String name, int age, String gender, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }
}
