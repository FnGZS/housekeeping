package com.houseWork.controller.front.user;

import com.houseWork.entity.QuartzJob;
import com.houseWork.entity.User;
import com.houseWork.security.bean.AuthenticationInfo;
import com.houseWork.security.bean.AuthorizationUser;
import com.houseWork.security.config.SelfUserDetailsService;
import com.houseWork.service.user.UserService;
import com.houseWork.utils.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SelfUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/add")
    public @ResponseBody String addUser() {
        userService.insertUser("xiaoming", "111111");
        return "插入成功";
    }

    @PostMapping(value = "/find")
    public @ResponseBody User queryUserByName(String username) {
        User userinfo = userService.findByname(username);
        return userinfo;
    }

    @PostMapping(value = "/findAll")
    public @ResponseBody List<QuartzJob> findAll() {
        return userService.findAll();
    }


    @PostMapping(value = "/jwtLogin")
    @ApiOperation(value = "登录授权", notes = "登录授权")
//    @Cacheable(value = "proUserLogin", key = "#authorizationUser.getUsername()")
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser) {
        final UserDetails jwtUser  = userDetailsService.loadUserByUsername(authorizationUser.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(authorizationUser.getPassword(),jwtUser.getPassword())){
            throw new AccountExpiredException("密码错误");
        }

        // 生成令牌
        final String token = JwtTokenUtil.generateToken(jwtUser.getUsername(), "_secret");

        // 返回 token
        return ResponseEntity.ok(new AuthenticationInfo(token,jwtUser));
    }
}
