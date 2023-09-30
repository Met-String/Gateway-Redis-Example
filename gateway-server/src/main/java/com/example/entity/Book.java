package com.example.entity;

import lombok.Data;

@Data
public class Book {
    int bid;
    String title;
    String desc;
    public Book(int bid, String title, String desc) {
        this.bid = bid;
        this.title = title;
        this.desc = desc;
    }
}

