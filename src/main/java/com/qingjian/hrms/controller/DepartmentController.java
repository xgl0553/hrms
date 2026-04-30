package com.qingjian.hrms.controller;

import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.entity.Department;
import com.qingjian.hrms.service.DepartmentService;
import com.qingjian.hrms.vo.DepartmentVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DepartmentService departmentService;

    /**
     * 部门查询不分页
     * @return
     */
    @ResponseBody
    @ApiOperation("部门查询不分页")
    @RequestMapping(value = "/dept/list",method = RequestMethod.POST)
    public CommonResult deptList(@RequestBody Department dept){
        List<DepartmentVO> depts = departmentService.list(dept);
        //返回结果
        CommonResult commonResult = CommonResult.success(depts);
        return commonResult;
    }
    /**
     * 部门添加
     * @return
     */
    @ResponseBody
    @ApiOperation("部门添加")
    @RequestMapping(value = "/dept/add",method = RequestMethod.POST)
    public CommonResult deptAdd(@RequestBody Department dept){
        departmentService.add(dept);
        //返回结果
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }
    /**
     * 部门查询
     * @return
     */
    @ResponseBody
    @ApiOperation("部门查询")
    @RequestMapping(value = "/dept/search/{deptId}",method = RequestMethod.GET)
    public CommonResult deptAdd(@PathVariable Integer deptId){
        Department department = departmentService.findById(deptId);
        //返回结果
        CommonResult commonResult = CommonResult.success(department);
        return commonResult;
    }

}
