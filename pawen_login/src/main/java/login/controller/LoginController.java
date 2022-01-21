package login.controller;

import login.pojo.Pawer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import login.pojo.User;
import login.service.LoginService;
import util.JwtUtil;
import util.Result;
import util.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User login = loginService.login(user);
        if(login==null){
            return new Result(false, StatusCode.LOGINERROR,"账号或密码错误");
        }
        Map<String,String> info = new HashMap<>();
        info.put("username",login.getUsername());
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(),login.getUsername(), null);
        String jwt2 = JwtUtil.createJWT(UUID.randomUUID().toString(),login.getUsername(), (long)60*60*1000*24);
        info.put("token",jwt);
        info.put("token_refresh",jwt2);
        return new Result(true, StatusCode.OK,"登录成功",info);
    }
    @PostMapping("/refresh")
    public Result refresh(@RequestParam String refreshtoken,@RequestParam String username){
        try {
            JwtUtil.parseJWT(refreshtoken);
        } catch (Exception e) {
            return new Result(false, StatusCode.ERROR,"刷新token失败",null);
        }
        Map<String,String> info = new HashMap<>();
        info.put("username",username);
        String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(),username, null);
        String jwt2 = JwtUtil.createJWT(UUID.randomUUID().toString(),username, (long)60*60*1000*24);
        info.put("token",jwt);
        info.put("token_refresh",jwt2);
        return new Result(true, StatusCode.OK,"登录成功",info);
    }
    @GetMapping("/getInfo")
    public Result getInfo(@RequestParam String username){
        Pawer pawer = loginService.getInfo(username);
        if(pawer==null){
            return new Result(false, StatusCode.ERROR,"获取用户信息异常",null);
        }
        return new Result(true, StatusCode.OK,"获取用户信息成功",pawer);
    }

}
