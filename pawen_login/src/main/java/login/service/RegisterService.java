package login.service;

import login.pojo.Pawer;
import login.pojo.User;
import org.springframework.stereotype.Service;


public interface RegisterService {
    void add(User user);
    void add(Pawer pawer);
    User findOne(String username);
}
