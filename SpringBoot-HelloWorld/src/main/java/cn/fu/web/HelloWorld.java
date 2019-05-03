package cn.fu.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorld {

    @GetMapping("hello1")
    public String hello(){
        log.info("hello method is running");
        return "hello world";
    }

    @GetMapping("hello2")
    public String hello2(){
        return "index";
    }
}
