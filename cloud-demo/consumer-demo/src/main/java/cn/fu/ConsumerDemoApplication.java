package cn.fu;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient   --eureka
//@EnableCircuitBreaker    --hystrix
//三合一  --> @SpringCloudApplication
@SpringCloudApplication
@EnableFeignClients         //--feign
public class ConsumerDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemoApplication.class, args);
    }
    /**
     * spring自带得rest客户端
     * @return
     */
    @Bean
    @LoadBalanced  //ribbo
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
