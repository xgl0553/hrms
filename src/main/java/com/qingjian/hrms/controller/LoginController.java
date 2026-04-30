package com.qingjian.hrms.controller;

import com.qingjian.hrms.common.CommonCode;
import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.entity.User;
import com.qingjian.hrms.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//@Slf4j
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     * @return
     */
    @ApiOperation("跳转登录页")
    @RequestMapping(value = "/loginPage",method = RequestMethod.GET)
    public String loginPage(){
        return "/login";
    }

    /**
     * 注册页面
     * @return
     */
    @ApiOperation("跳转注册页")
    @RequestMapping(value = "/registerPage",method = RequestMethod.GET)
    public String registerPage(){
        return "/register";
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation("退出登录")
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logPage(HttpServletRequest request){
        request.getSession().removeAttribute(CommonCode.SESSION_USER);
        return "/login";
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @ApiOperation("登录")
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        CommonResult commonResult = new CommonResult();
        //1、校验参数
        if(user == null || StringUtils.isEmpty(user.getUsername())||StringUtils.isEmpty(user.getPaword())){
            commonResult.setCode(CommonCode.FAIL);
            commonResult.setMsg("用户名和密码不能为空");
            return commonResult;
        }
        //2、根据用户名查询用户
        User userByUsername = userService.getUserByUsername(user.getUsername());
        if(userByUsername!=null){
            //2-1、成功，将用户信息放入session，更新最新登录时间
            if(userByUsername.getPaword().equals(user.getPaword())){
                //更新最新登录时间
                userByUsername.setLastLogineTime(new Date());
                userService.edit(userByUsername);
                commonResult.setCode(CommonCode.SUCCESS);
                request.getSession().setAttribute(CommonCode.SESSION_USER,user);
            }else{//2-2、失败，提示失败信息
                commonResult.setCode(CommonCode.FAIL);
                commonResult.setMsg("密码不正确");
            }
        }else{
            commonResult.setCode(CommonCode.FAIL);
            commonResult.setMsg("用户名不正确");
        }
        return commonResult;
    }

    /**
     * 注册
     * @return
     */
    @ApiOperation("注册")
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public CommonResult register(@RequestBody User user, HttpServletRequest request, HttpServletResponse response){
        logger.info("register:" + user);
        CommonResult commonResult = new CommonResult();
        //1、校验参数
        if(user == null || StringUtils.isEmpty(user.getUsername())||StringUtils.isEmpty(user.getPaword())){
            commonResult.setCode(CommonCode.FAIL);
            commonResult.setMsg("用户名和密码不能为空");
            return commonResult;
        }
        //2、根据用户名查找，如果存在，提示已存在
        User userByUsername = userService.getUserByUsername(user.getUsername());
        if(userByUsername!=null){
            commonResult.setCode(CommonCode.FAIL);
            commonResult.setMsg("用户名已存在");
        }else{//3、如果不存在，将加密后的用户信息存储
            //设置创建时间，新增用户
            user.setCreatetime(new Date());
            int result = userService.addUser(user);
            if(result != 0){
                commonResult.setCode(CommonCode.SUCCESS);
            }else{
                commonResult.setCode(CommonCode.FAIL);
                commonResult.setMsg("系统原因，请重试或联系管理员");
            }
        }
        return commonResult;
    }

    /*public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes()));
    }*/
}
