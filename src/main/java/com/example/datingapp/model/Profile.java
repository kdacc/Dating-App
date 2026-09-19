package com.example.datingapp.model;

import java.util.List;

public class Profile {
    private Long id;
    private String name;
    private Integer age;
    private List<String> keywords;
    private String openInfo;
    private String closedInfo;

    public Profile() {}

    public Profile(Long id, String name, Integer age, List<String> keywords,
                   String openInfo, String closedInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.keywords = keywords;
        this.openInfo = openInfo;
        this.closedInfo = closedInfo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public List<String> getKeywords() { return keywords; }
    public void setKeywords(List<String> keywords) { this.keywords = keywords; }

    public String getOpenInfo() { return openInfo; }
    public void setOpenInfo(String openInfo) { this.openInfo = openInfo; }

    public String getClosedInfo() { return closedInfo; }
    public void setClosedInfo(String closedInfo) { this.closedInfo = closedInfo; }
}