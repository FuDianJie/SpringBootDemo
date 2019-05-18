package cn.fu.user.service;

import cn.fu.user.mapper.UserMapper;
import cn.fu.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
    @Transient
    public void insertFu(User user) {
        userMapper.insert(user);
    }
}
