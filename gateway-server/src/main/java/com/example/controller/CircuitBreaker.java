package com.example.controller;
import com.example.entity.Book;
import com.example.entity.Borrow;
import com.example.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//这些事断路器，毕竟网关就是网关，总有无法访问资源的时候（对于这个池沼网关是再也无法访问到了），因此我们会在适当的时候（永远），为用户
//反馈一些临时的数据来搪塞。如果访问失败了，返回编造的数据不就好了嘛（智将）！
@RestController
public class CircuitBreaker {
    @RequestMapping("/WhenTailToUseBookService")
    public Book WhenTailToUseBookService(){
        return new Book(-1, "卡兹戴尔战争史", "独眼谍报员写的，不怎么样");
    }

    @RequestMapping("/WhenTailToUseUserService")
    public User WhenTailToUseUserService(){
        return new User(-1, "特雷西斯" , "724", "男");
    }

    @RequestMapping("/WhenTailToUseBorrowService")
    public Borrow WhenTailToUseBorrowService(){
        return new Borrow(-11111, -111111, -11111);
    }
}
