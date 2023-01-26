package com.example.attendancemonitoringsystemupes;

public class Person {
    private String name;
    private String type;
    private String sapId;


    public Person(String name,String type, String sapId) {
        this.name = name;
        this.type = type;
        this.sapId = sapId;
    }

    public String getName() {
        return name;
    }



    public String getType() {
        return type;
    }

    public String getSapId() {
        return sapId;
    }
}
