package cn.fu.consumer.web;

import cn.fu.consumer.client.UserClient;
import cn.fu.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("consumer")
@Slf4j
@DefaultProperties(defaultFallback = "selectByIdShiBai")
public class ConsumerController {
    @Autowired
    private User user;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;
//    @Autowired
//    private RibbonLoadBalancerClient ribbonLoadBalancerClient;
    @Autowired
    private UserClient userClient;

    @GetMapping("{id}")
    public User selectById(@PathVariable("id") Integer id){
        //根据服务id获取实例
        List<ServiceInstance> instances = discoveryClient.getInstances("USER-SERVICE");
        System.out.println("一共有"+instances.size()+"个实例");
        //从实例中去除ip和端口
        ServiceInstance instance = instances.get(0);
        String host = instance.getHost(); //ip
        int port = instance.getPort();    //端口
        String url = "http://"+host+":"+port+"/user/"+id;
        return restTemplate.getForObject(url,User.class);
    }
    @GetMapping("/2/{id}")
    public User selectById2(@PathVariable("id") Integer id){
        //根据服务id获取实例  ribbon实现负载均衡  返回单个实例而不是集合
//        ServiceInstance instance = ribbonLoadBalancerClient.choose("USER-SERVICE");
//        String host = instance.getHost(); //ip
//        int port = instance.getPort();    //端口
//        String url = "http://"+host+":"+port+"/user/"+id;
        String url = "http://USER-SERVICE/user/"+ id;
        return restTemplate.getForObject(url,User.class);
    }
    @GetMapping("/3/{id}")
    @HystrixCommand(fallbackMethod = "selectByIdShiBai")   //失败熔断  线程隔离  是  降级处理
    public String selectById3(@PathVariable("id") Integer id){
        String url = "http://USER-SERVICE/user/"+ id;
        return restTemplate.getForObject(url,String.class);
    }

    @GetMapping("/4/{id}")
                            //设置超时时长3秒
    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")})
    public String selectById4(@PathVariable("id") Integer id){
        String url = "http://USER-SERVICE/user/"+ id;
        return restTemplate.getForObject(url,String.class);
    }
    public String selectByIdShiBai(@PathVariable("id") Integer id){
        return "服务器繁忙";
    }
    public String selectByIdShiBai(){ //通用回调函数 无参
        return "服务器繁忙！";
    }

    @GetMapping("/5/{id}")
    @HystrixCommand(
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),    //请求量阈值   10次一检验
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), //睡眠窗时长   短路超过10秒 会尝试去请求一次
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")   //错误请求百分比
            }

    )   //测试熔断器 熔断效果 打开  关闭  半开
    public String selectById5(@PathVariable("id") Integer id){
        if(id % 2 == 0){
            throw new RuntimeException("");
        }
        String url = "http://USER-SERVICE/user/"+ id;
        return restTemplate.getForObject(url,String.class);
    }

    @GetMapping("/6/{id}")
    public User queryById(@PathVariable("id") Long id){
        return userClient.queryById(id);
    }
}
