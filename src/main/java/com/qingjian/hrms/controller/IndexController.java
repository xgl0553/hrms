package com.qingjian.hrms.controller;

import com.qingjian.hrms.common.CommonCode;
import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.entity.User;
import com.qingjian.hrms.qo.UserQO;
import com.qingjian.hrms.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    /**
     * 首页
     * @return
     */
    @ApiOperation("首页")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String loginPage(HttpServletRequest request, Model model){
        User user = (User)request.getSession().getAttribute(CommonCode.SESSION_USER);
        model.addAttribute("user",user);
        return "/index";
    }
    /**
     * 错误页
     * @return
     */
    @ApiOperation("错误页")
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public String error(){
        return "/404";
    }


    /**
     * 部门
     * @return
     */
    @ApiOperation("部门")
    @RequestMapping(value = "/dept",method = RequestMethod.GET)
    public String dept(){
        return "/employee/dept";
    }

    /**
     * 员工
     * @return
     */
    @ApiOperation("员工列表页")
    @RequestMapping(value = "/employee",method = RequestMethod.GET)
    public String employee(){
        return "/employee/employee";
    }

    /**
     * 账号
     * @return
     */
    @ApiOperation("账号列表页")
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String user(){
        return "/employee/user";
    }

    /**
     * 考勤页
     * @return
     */
    @ApiOperation("考勤列表页")
    @RequestMapping(value = "/worktime",method = RequestMethod.GET)
    public String worktime(){
        return "/employee/worktime";
    }
    /**
     * 工资页
     * @return
     */
    @ApiOperation("工资列表页")
    @RequestMapping(value = "/salary",method = RequestMethod.GET)
    public String salary(){
        return "/employee/salary";
    }


    /**
     * 修改密码
     * @return
     */
    @ResponseBody
    @ApiOperation("修改密码")
    @RequestMapping(value = "/editPaword",method = RequestMethod.POST)
    public CommonResult editPaword(@RequestBody UserQO userQO, HttpServletRequest request){
        CommonResult commonResult = new CommonResult();
        User user = (User) request.getSession().getAttribute(CommonCode.SESSION_USER);
        if(! user.getPaword().equals(DigestUtils.md5DigestAsHex(userQO.getOldpaword().getBytes()))){
            commonResult = CommonResult.fail("原密码不正确");
            return commonResult;
        }
        user.setPaword(DigestUtils.md5DigestAsHex(userQO.getPaword().getBytes()));
        request.getSession().setAttribute(CommonCode.SESSION_USER,user);
        userService.edit(user);
        commonResult = CommonResult.success();
        return commonResult;
    }
}
