package com.example.entity;

import lombok.Data;

@Data
public class User {
    int uid;
    String name;
    String age;
    String sex;

    public User(int uid, String name, String age, String sex) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
}
