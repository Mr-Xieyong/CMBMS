package com.xy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xy.base.ResponseVo;
import com.xy.constant.BaseConstant;
import com.xy.entity.dos.User;
import com.xy.entity.vos.LoginVo;
import com.xy.entity.vos.UserVo;
import com.xy.enums.ErrorEnum;
import com.xy.mapper.UserMapper;
import com.xy.service.UserService;
import com.xy.util.MD5Util;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:51
 */
@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private DozerBeanMapper dozerBeanMapper;


    @Override
    public Integer Login(LoginVo loginVo) {
        return null;
    }

    @Override
    public Integer Regist(UserVo userVo) {

        String tempPwd = MD5Util.getSaltMD5(userVo.getPassWord());
        User user = new User();
        dozerBeanMapper.map(userVo,user);

        user.setPassWord(tempPwd);
        user.setDelFlag(BaseConstant.ONE);
        userMapper.insert(user);

        Integer userId = user.getUserId();
        return userId;
    }

    public int updateUserInfo(UserVo userVo) {

        userVo.setUpdateBy(userVo.getUserId());
        userVo.setUpdateDate(new Date());

        User user = new User();
        dozerBeanMapper.map(userVo,user);
        userMapper.updateById(user);
        return 1;
    }


}
