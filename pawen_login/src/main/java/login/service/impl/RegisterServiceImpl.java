package login.service.impl;

import login.dao.PawerMapper;
import login.dao.UserMapper;
import login.pojo.Pawer;
import login.pojo.User;
import login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PawerMapper pawerMapper;
    @Override
    public void add(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void add(Pawer pawer) {
        pawerMapper.insertSelective(pawer);
    }

    @Override
    public User findOne(String username) {
       return userMapper.selectByPrimaryKey(username);
    }
}
