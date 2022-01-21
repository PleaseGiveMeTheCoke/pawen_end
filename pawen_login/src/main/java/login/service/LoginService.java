package login.service;

import login.pojo.Pawer;
import login.pojo.User;

public interface LoginService {
    User login(User user);

    Pawer getInfo(String username);
}
