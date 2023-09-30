package com.example.entity;

import lombok.Data;

@Data
public class Borrow {
    int id;
    int uid;
    int bid;

    public Borrow(int id, int uid, int bid) {
        this.id = id;
        this.uid = uid;
        this.bid = bid;
    }
}
