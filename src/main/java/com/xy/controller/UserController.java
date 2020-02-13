package com.xy.controller;

import com.xy.base.ResponseVo;
import com.xy.entity.vos.LoginVo;
import com.xy.entity.vos.UserVo;
import com.xy.enums.ErrorEnum;
import com.xy.exception.BusinessException;
import com.xy.service.UserService;
import com.xy.util.CaptchaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xieyong
 * @date 2019/12/16 - 12:39
 */
@Api(value = "用户信息", description = "账号设置逻辑")
@RestController
@RequestMapping("/userController")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseVo Login(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) throws IOException, ServletException {

        Map<String, Object> map = new HashMap<>();
        // 编码
        request.setCharacterEncoding("utf-8");
        // 获取请求参数
        /*
            拿到页面传过来的手动输入的验证码, 该验证码要和生成图片上的
            文本验证码比较, 如果相同, 验证码输入成功!
         */
        String imageText = request.getParameter("captcha");
        // 图片的验证码
        String text = (String) request.getSession().getAttribute("randomString");

        if (!text.equalsIgnoreCase(imageText)) {
            request.setAttribute("imageMess", "验证码输入错误!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        // 获取用户名和密码
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");

        //开始对用户进行校验
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(passWord)) {
            throw new BusinessException(ErrorEnum.USER_ACCOUNT_UNKNOWN);
        }
//        if (userService.isExist(userName, passWord) != BaseConstant.ZERO) {
//            // 将用户信息保存到session中
//            request.getSession().setAttribute("username", username);
//
//            // 使用cookie实现回写用户名
//            Cookie cookie = new Cookie("username", username);
//            cookie.setMaxAge(60 * 60);
//            // 通过响应头发送cookie
//            response.addCookie(cookie);
//            // 重定向登录成功界面
//            response.sendRedirect(request.getContextPath() + "/page/index.jsp");
//        } else {
//            request.setAttribute("error", "用户名或密码错误!");
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
//        }
        return new ResponseVo();
    }


    /**
     * 注册个人用户
     * @param request
     * @param response
     * @param userVo
     * @return
     */
    @ApiOperation(value = "用户注册", notes = "用户注册")
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ResponseVo regist(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVo userVo) {

        if (StringUtils.isEmpty(userVo.getUserName())) {
            return new ResponseVo(ErrorEnum.NAME_NOT_NULL);
        }
        if (StringUtils.isEmpty(userVo.getPassWord())) {
            return new ResponseVo(ErrorEnum.PASSWORD_NOT_NULL);
        }
        if (StringUtils.isEmpty(userVo.getUserType())) {
            return new ResponseVo(ErrorEnum.USERTYPE_NOT_NULL);
        }
        if (StringUtils.isEmpty(userVo.getIdentityNumber())) {
            return new ResponseVo(ErrorEnum.IDENTITYNUMBER_BOT_NULL);
        }
        if (StringUtils.isEmpty(userVo.getPhone())) {
            return new ResponseVo(ErrorEnum.PHONTNUMBER_ERROR);
        }

        userVo.setCreateDate(new Date());

        int ret = userService.Regist(userVo);

        return new ResponseVo(ErrorEnum.SUCCESS, ret);
    }

    /**
     * 注册个人用户
     * @param request
     * @param response
     * @param userVo
     * @return
     */
    @ApiOperation(value = "用户个人信息修改", notes = "用户个人信息修改")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public ResponseVo updateUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody UserVo userVo) {

        int ret = userService.updateUserInfo(userVo);
        return new ResponseVo(ErrorEnum.SUCCESS,ret);
    }



    @ApiOperation(value = "验证码", notes = "验证码")
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CaptchaUtil.outputCaptcha(request, response);
    }

}

