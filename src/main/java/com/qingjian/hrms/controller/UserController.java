package com.qingjian.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.common.PageCommonResult;
import com.qingjian.hrms.entity.User;
import com.qingjian.hrms.qo.UserQO;
import com.qingjian.hrms.service.UserService;
import com.qingjian.hrms.vo.EmployeeVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;



    /**
     * 账号
     * @return
     */
    @ResponseBody
    @ApiOperation("账号查询")
    @RequestMapping(value = "/user/list",method = RequestMethod.POST)
    public PageCommonResult userList(@RequestBody UserQO userQO){
        PageHelper.startPage(userQO.getPage(),userQO.getLimit());
        List<User> userList = userService.list(userQO);
        PageInfo<EmployeeVO> pageInfo = new PageInfo(userList);

        //返回结果
        PageCommonResult commonResult = PageCommonResult.success(pageInfo.getList(),pageInfo.getTotal());
        return commonResult;
    }
    /**
     * 账号
     * @return
     */
    @ResponseBody
    @ApiOperation("账号")
    @RequestMapping(value = "/user/search/{id}",method = RequestMethod.GET)
    public CommonResult userSearch(@PathVariable Integer id){
        User user = userService.findById(id);
        CommonResult commonResult = CommonResult.success(user);
        return commonResult;
    }
    /**
     * 账号删除
     * @return
     */
    @ResponseBody
    @ApiOperation("账号删除")
    @RequestMapping(value = "/user/delete/{id}",method = RequestMethod.DELETE)
    public CommonResult userDelete(@PathVariable Integer id){
        userService.deleteById(id);
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }
    /**
     * 账号添加
     * @return
     */
    @ResponseBody
    @ApiOperation("账号添加")
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public CommonResult userAdd(@RequestBody User user){
        //返回结果
        CommonResult commonResult = new CommonResult();
        //验证username不重复
        User userByUsername = userService.getUserByUsername(user.getUsername());
        if(userByUsername!= null){//数据库中存在该用户名用户
            if(user.getId()== null || (user.getId()!= null && userByUsername.getId() != user.getId())){
                //修改用户,当前username和数据库用户不是同一个，判断为重复;添加用户，判断为重复
                commonResult = CommonResult.fail("用户名已存在");
                return commonResult;
            }
        }
        if(user!=null && user.getId()!=null){
            User userDB = userService.findById(user.getId());
            userDB.setUsername(user.getUsername());
            userDB.setRealname(user.getRealname());
            userService.editSelective(userDB);//只修改用户名、姓名部分，其他不可修改
        }else{
            user.setPaword(DigestUtils.md5DigestAsHex(user.getPaword().getBytes()));
            user.setCreatetime(new Date());
            userService.add(user);
        }

        //返回结果
        commonResult = CommonResult.success();
        return commonResult;
    }
    /**
     * 修改密码
     * @return
     */
    @ResponseBody
    @ApiOperation("修改密码")
    @RequestMapping(value = "/user/editPaword/{id}",method = RequestMethod.POST)
    public CommonResult editPaword(@RequestBody User user, @PathVariable Integer id){
        User userDb = userService.findById(id);
        userDb.setPaword(DigestUtils.md5DigestAsHex(user.getPaword().getBytes()));
        userService.edit(userDb);
        //返回结果
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }
}
