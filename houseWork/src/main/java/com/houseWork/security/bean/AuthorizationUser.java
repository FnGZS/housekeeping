package com.houseWork.security.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * 授权用户
 * </pre>
 * @author zjw
 */
@Data
@Getter
@Setter
@Builder
public class AuthorizationUser {

    //@NotBlank
    @ApiModelProperty("用户名")
    private String username;

    //@NotBlank
    @ApiModelProperty("密码")
    private String password;

}
