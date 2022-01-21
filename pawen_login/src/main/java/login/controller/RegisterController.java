package login.controller;

import login.pojo.Pawer;
import login.pojo.RegisterUser;
import login.pojo.User;
import login.service.LoginService;
import login.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import util.JwtUtil;
import util.Result;
import util.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class RegisterController {
    @Autowired
    RegisterService registerService;
    @PostMapping("/register")
    public Result register(@RequestBody RegisterUser user){

        String username = user.getUsername();
        User one = registerService.findOne(username);
        if(one!=null){
            return new Result(false, StatusCode.ERROR,"用户名重复");
        }
        User user1 = new User(username,user.getPass());
        Pawer pawer = new Pawer(username,user.getPass(),user.getNickname(),null);
        registerService.add(user1);
        registerService.add(pawer);
        return new Result(true, StatusCode.OK,"注册成功");
    }
}
