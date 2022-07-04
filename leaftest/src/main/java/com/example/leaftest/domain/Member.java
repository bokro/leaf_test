package com.example.leaftest.domain;

public class Member {
    //롬복이 있었다면 압축하지 않았을까
    private Long id;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    private String name;

}
