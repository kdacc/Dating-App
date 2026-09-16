package com.example.datingapp.model;

import java.util.List;

public class Profile {
    private Long id;
    private String name;
    private int age;
    private List<String> keywords;
    private String openInfo;
    private String closedInfo;

    public Profile(Long id, String name, int age, List<String> keywords,
                   String openInfo, String closedInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.keywords = keywords;
        this.openInfo = openInfo;
        this.closedInfo = closedInfo;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public List<String> getKeywords() { return keywords; }
    public String getOpenInfo() { return openInfo; }
    public String getClosedInfo() { return closedInfo; }
}