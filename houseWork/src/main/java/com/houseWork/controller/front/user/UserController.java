package com.houseWork.controller.front.user;

import com.github.pagehelper.PageHelper;
import com.houseWork.entity.User;
import com.houseWork.security.bean.AuthenticationInfo;
import com.houseWork.security.bean.AuthorizationUser;
import com.houseWork.security.config.SelfUserDetailsService;
import com.houseWork.service.user.UserService;
import com.houseWork.utils.JwtTokenUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SelfUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    @ApiOperation(value = "添加用户", notes = "添加用户")
    public ResponseEntity addUser(@Validated @RequestBody User user){
        userService.addUser(user);
        return new ResponseEntity("", HttpStatus.OK);
    }

    @PostMapping("/userList")
    @ApiOperation(value = "",notes = "")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "id", dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "username", value = "名字", dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "pageNum", value = "页码", required = false, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页显示数量", required = false, dataType = "int")
    })
    public ResponseEntity userList(@RequestParam(required = false) Integer questionId,
                                   @RequestParam(required = false) String userName,
                                   @RequestParam(defaultValue = "0") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize){
        Map map = new HashMap();
        PageHelper.startPage(pageNum,pageSize);
        return new ResponseEntity("",HttpStatus.OK);
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
