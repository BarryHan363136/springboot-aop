package com.bmw.azure.oap.controller;

import com.bmw.azure.oap.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/8 10:52
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private AtomicInteger atomicInteger = new AtomicInteger();

    private Map<String, User> map = new ConcurrentHashMap<>();

    @GetMapping("/list")
    public Map list(){
        log.info("2222222222222222222222");
        return map;
    }

    @GetMapping("/add")
    public Map add(){
        String id = atomicInteger.incrementAndGet()+"";
        User user = new User();
        user.setId(id);
        user.setName("zhangsan");
        user.setEmail("lxm@bmw.com");
        map.put(id, user);

        User user2 = new User();
        user2.setId(atomicInteger.incrementAndGet()+"");
        user2.setName("zhangsan");
        user2.setEmail("lxm@bmw.com");
        map.put(atomicInteger.incrementAndGet()+"", user2);

        User user3 = new User();
        user3.setId(atomicInteger.incrementAndGet()+"");
        user3.setName("zhangsan");
        user3.setEmail("lxm@bmw.com");
        map.put(atomicInteger.incrementAndGet()+"", user3);

        return map;
    }



}
