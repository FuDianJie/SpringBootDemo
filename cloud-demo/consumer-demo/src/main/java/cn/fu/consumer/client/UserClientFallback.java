package cn.fu.consumer.client;

import cn.fu.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        User user = new User();
        user.setName("未查询到用户 ！ ");
        return user;
    }
}
