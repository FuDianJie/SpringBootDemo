package cn.fu.service;

import cn.fu.mapper.UserMapper;
import cn.fu.pojo.Fu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Fu queryById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }
    @Transient
    public void insertFu(Fu fu) {
        userMapper.insert(fu);
    }
}
