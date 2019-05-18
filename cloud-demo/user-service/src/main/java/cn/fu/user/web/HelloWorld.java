package cn.fu.user.web;

import cn.fu.consumer.pojo.User;
import cn.fu.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorld {
    @Autowired
    private User user;
    @Autowired
    private UserService userService;

    @GetMapping("hello1")
    public String hello(){
        log.info("hello method is running");
        return "hello world";
    }

    @GetMapping("hello2")
    public String hello2(){
        return "index";
    }

    @GetMapping("user/{id}")
    public User selectById(@PathVariable("id") Integer id) throws InterruptedException {
        //Thread.sleep(2000L);
        //System.out.println("模拟超时");
        return userService.queryById(id);
    }

}
