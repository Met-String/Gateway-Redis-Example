package com.example.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
public class FailToGetKeyController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

//  @Transactional(isolation = Isolation.SERIALIZABLE)
    @RequestMapping("/FailToGetKey")
    public String FailToGetKey(){

//      开始随机生成一个6位数的密钥，之后以MyKey为键存入Redis中，但是缓存时间只有5分钟，5分钟之内没有被使用的话，密钥生涯就结束了罢
        Random random = new Random();
        int key = random.nextInt(899999) + 100000;
        stringRedisTemplate.opsForValue().set("MyKey",Integer.toString(key),5, TimeUnit.MINUTES);

//      将密钥写入一个MyKey.txt文件，您需要打开这个MyKey文件才能看到目前的密钥，池沼Met不会用验证码（悲），只能采用这种LowB的操作了。
        try {
            FileWriter fileWriter = new FileWriter("MyKey.txt");
            fileWriter.write(Integer.toString(key));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//      管理员是谁？怎么联系？推荐回答：我不知道。
        return "您的请求非法，这代表您的访问请求不含有密钥或密钥错误,新的密钥已经发放,如有疑问，清联系管理员。";
    }
}
