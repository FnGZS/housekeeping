package com.houseWork.security.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;


/**
 * <pre>
 * 身份验证信息
 * </pre>
 * @author zjw
 */
@Getter
@AllArgsConstructor
public class AuthenticationInfo implements Serializable {

    private final String token;

    private final UserDetails user;
}
