package com.xy.entity.vos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Xieyong
 * @date 2019/12/16 - 13:02
 */
@Data
public class LoginVo implements Serializable{

    @NotNull(message = "用户名不能为空")
    private String loginName;
    @NotNull(message = "用户密码不能为空")
    private String passWord;
    @NotNull(message = "验证码不能为空")
    private String captcha;
}
