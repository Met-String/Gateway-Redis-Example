package com.example.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


import java.net.URI;
import java.util.List;

//全局过滤器，十分糟心的东西，具体创建方式参考了SpringGateway的官方示例。
@Component
public class GlobleFilter implements GlobalFilter, Ordered {
    @Autowired
    StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

    @Override
//      此处的全局过滤器将同时承担识别密钥的作用,如果客户端HTTP请求参数中key值不等于指定密钥则拒绝访问
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

//      首先获取Redis中存储的密钥...如果Redis中有的话，当然第一次打开的时候是没有的
        String Misteryoso = stringRedisTemplate.opsForValue().get("MyKey");
        System.out.println("当前密钥是"+Misteryoso);

//      之后从HTTP请求里面把参数Key的值读出来，当然这个需要用户手动地去添加，例如：localhost:8500?key=123456
        ServerHttpRequest serverHttpRequest =  exchange.getRequest();
        System.out.println(serverHttpRequest.getQueryParams());
        List<String> value = serverHttpRequest.getQueryParams().get("key");
        System.out.println("用户输入密钥是"+value);

//      当然这里如果是第一次运行，那么Misteryoso是什么都没有的，因此一定会失败，如果Value值不对也会失败。
        if(value != null && value.contains(Misteryoso)){
            //如果成功了就能够继续前往下一个过滤器了（喜）
            return chain.filter(exchange);
        }else {
            //失败了的话只能终结这次请求，并且跳转到失败页面(悲)
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.SEE_OTHER);
            response.getHeaders().setLocation(URI.create("/FailToGetKey"));
            return response.setComplete();
        }
    }

    //过滤器优先级设置为最高，如果没有密钥的话，就不用进行后面的过滤了
    @Override
    public int getOrder() {
        return 0;
    }
}
