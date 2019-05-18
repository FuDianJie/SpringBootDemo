package cn.fu.consumer.client;

import cn.fu.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**使用 Feign 1.引依赖
 *           2. 启动类上加注解 @EnableFeignClients
 *           3.写接口
 *           4.controller 类 调用此接口
 *           注意  可以省ribbon的启动器  但是hystrix 不能省
 *           如果使用hystrix  就添加fallback  并实现接口
 */
@FeignClient(value = "user-service" ,fallback = UserClientFallback.class)
public interface UserClient{

    @GetMapping("user/{id}")
    User queryById(@PathVariable("id") Long id);
}
