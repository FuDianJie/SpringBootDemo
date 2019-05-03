package cn.fu.web;

import cn.fu.pojo.Fu;
import cn.fu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorld {
    @Autowired
    private Fu fu;
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
    public Fu selectById(@PathVariable("id") Integer id){
        return userService.queryById(id);
    }

}
