package com.example.datingapp.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "profiles")
@NamedQuery(name = "Profile.findByKeywordNamed", query = "SELECT p FROM Profile p WHERE p.keywordsStr LIKE :keyword")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    @Column(name = "keywords")
    private String keywordsStr;

    @Column(name = "open_info")
    private String openInfo;

    @Column(name = "closed_info")
    private String closedInfo;

    public Profile() {}

    public Profile(Long id, String name, Integer age, List<String> keywords,
                   String openInfo, String closedInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        setKeywords(keywords);
        this.openInfo = openInfo;
        this.closedInfo = closedInfo;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public List<String> getKeywords() {
        if (keywordsStr == null || keywordsStr.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return Arrays.asList(keywordsStr.split(","));
    }

    public void setKeywords(List<String> keywords) {
        if (keywords == null || keywords.isEmpty()) {
            this.keywordsStr = "";
        } else {
            this.keywordsStr = String.join(",", keywords);
        }
    }

    public String getOpenInfo() { return openInfo; }
    public void setOpenInfo(String openInfo) { this.openInfo = openInfo; }

    public String getClosedInfo() { return closedInfo; }
    public void setClosedInfo(String closedInfo) { this.closedInfo = closedInfo; }
}