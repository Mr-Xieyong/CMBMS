package com.xy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xy.entity.dos.User;
import com.xy.entity.vos.LoginVo;
import com.xy.entity.vos.UserVo;


/**
 * @author Xieyong
 * @date 2019/12/16 - 12:51
 */
public interface UserService extends IService<User> {


    Integer Login(LoginVo loginVo);

    Integer Regist(UserVo userVo);

    int updateUserInfo(UserVo userVo);

}
