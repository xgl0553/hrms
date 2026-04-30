package com.qingjian.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.common.PageCommonResult;
import com.qingjian.hrms.entity.Employee;
import com.qingjian.hrms.qo.EmployeeQO;
import com.qingjian.hrms.service.EmployeeService;
import com.qingjian.hrms.vo.EmployeeVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EmployeeService employeeService;


    /**
     * 员工查询分页
     * @return
     */
    @ResponseBody
    @ApiOperation("员工查询")
    @RequestMapping(value = "/employee/list",method = RequestMethod.POST)
    public PageCommonResult employeeList(@RequestBody EmployeeQO employee){
        PageHelper.startPage(employee.getPage(),employee.getLimit());
        List<EmployeeVO> employeeList = employeeService.list(employee);
        PageInfo<EmployeeVO> pageInfo = new PageInfo(employeeList);
        //返回结果
        PageCommonResult commonResult = PageCommonResult.success(pageInfo.getList(),pageInfo.getTotal());
        return commonResult;
    }
    /**
     * 单个员工详细信息查询
     * @return
     */
    @ResponseBody
    @ApiOperation("员工查询")
    @RequestMapping(value = "/employee/search/{id}",method = RequestMethod.GET)
    public CommonResult employeeSearch(@PathVariable Integer id){
        Employee employee = employeeService.findById(id);
        CommonResult commonResult = CommonResult.success(employee);
        return commonResult;
    }
    /**
     * 员工查询
     * @return
     */
    @ResponseBody
    @ApiOperation("员工查询")
    @RequestMapping(value = "/employee/searchAll",method = RequestMethod.POST)
    public CommonResult employeeSearchAll(@RequestBody EmployeeQO employeeQO){
        List<EmployeeVO> employeeList = employeeService.findByEmployee(employeeQO);
        CommonResult commonResult = CommonResult.success(employeeList);
        return commonResult;
    }
    /**
     * 员工删除
     * @return
     */
    @ResponseBody
    @ApiOperation("员工删除")
    @RequestMapping(value = "/employee/delete/{id}",method = RequestMethod.DELETE)
    public CommonResult employeeDelete(@PathVariable Integer id){
        employeeService.deleteById(id);
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }
    /**
     * 员工添加或修改
     * @return
     */
    @ResponseBody
    @ApiOperation("员工添加或修改")
    @RequestMapping(value = "/employee/add",method = RequestMethod.POST)
    public CommonResult employeeAdd(@RequestBody Employee employee){
        if(employee!=null && employee.getId()!=null){
            employeeService.edit(employee);
        }else{
            employeeService.add(employee);
        }
        //返回结果
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }
}
