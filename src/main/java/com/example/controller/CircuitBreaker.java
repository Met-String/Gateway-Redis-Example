package com.example.controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.LinkedHashMap;


//这些事断路器，毕竟网关就是网关，总有无法访问资源的时候（对于这个池沼网关是再也无法访问到了），因此我们会在适当的时候（永远），为用户
//反馈一些临时的数据来搪塞。如果访问失败了，返回编造的数据不就好了嘛（智将）！

@RestController
public class CircuitBreaker {
    @RequestMapping("/WhenTailToUseBookService")
    public HashMap<Object,Object> WhenTailToUseBookService(){
//      这里使用LinkedHashMap是为了插入的键有序，否则你会发现打印出来是按照Key值字母先后顺序去排列的..
        HashMap hashMap =new LinkedHashMap();
        hashMap.put("Title","卡兹戴尔战争史");
        hashMap.put("Author","U酱单推人,独眼谍报员");
        hashMap.put("Desc","特雷西斯看完之后点名批评文笔不怎么样");
        return hashMap;
    }

    @RequestMapping("/WhenTailToUseUserService")
    public HashMap<Object,Object> WhenTailToUseUserService(){
        HashMap hashMap = new LinkedHashMap();
        hashMap.put("UserName","特雷西斯");
        hashMap.put("UserAge",786);
        hashMap.put("Gender","男");
        hashMap.put("Desc","在请求失败时堂堂出现的神秘萨卡兹男性！");
        return hashMap;
    }

    @RequestMapping("/WhenTailToUseBorrowService")
    public HashMap<Object,Object> WhenTailToUseBorrowService(){
        HashMap hashMap = new LinkedHashMap();
        hashMap.put("UserName","特蕾西娅");
        hashMap.put("BookTitle","哥伦比亚时装潮流-188期");
        return hashMap;
    }
}
