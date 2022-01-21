package login.service.impl;

import login.dao.PawerMapper;
import login.dao.UserMapper;
import login.pojo.Pawer;
import org.springframework.beans.factory.annotation.Autowired;
import login.pojo.User;
import login.service.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PawerMapper pawerMapper;
    @Override
    public User login(User user) {
        User userr = new User();
        userr.setPassword(user.getPassword());
        userr.setUsername(user.getUsername());
        User user1 = userMapper.selectOne(userr);
        return user1;
    }

    @Override
    public Pawer getInfo(String username) {
        Pawer p = new Pawer();
        p.setUsername(username);
        Pawer pawer = pawerMapper.selectOne(p);
        return pawer;
    }
}
