package com.qingjian.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.common.PageCommonResult;
import com.qingjian.hrms.entity.Salary;
import com.qingjian.hrms.qo.SalaryQO;
import com.qingjian.hrms.service.SalaryService;
import com.qingjian.hrms.vo.SalaryVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    /**
     * 工资查询
     * @return
     */
    @ResponseBody
    @ApiOperation("工资查询")
    @RequestMapping(value = "/salary/list",method = RequestMethod.POST)
    public PageCommonResult workList(@RequestBody SalaryQO salaryQO){
        PageHelper.startPage(salaryQO.getPage(),salaryQO.getLimit());
        List<SalaryVO> salaryVOS = salaryService.list(salaryQO);
        PageInfo<SalaryVO> pageInfo = new PageInfo(salaryVOS);
        //返回结果
        PageCommonResult commonResult = PageCommonResult.success(pageInfo.getList(),pageInfo.getTotal());
        return commonResult;
    }

    /**
     * 生成工资
     * @return
     */
    @ResponseBody
    @ApiOperation("生成工资")
    @RequestMapping(value = "/salary/generate",method = RequestMethod.POST)
    public CommonResult imitateCheck(@RequestBody Salary salary){
        if(salary.getId() != null &&  !salary.getId().equals("")){
            salaryService.edit(salary);//修改
        }else{
            salaryService.add(salary);//新增
        }
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }

    /**
     * 查询工资
     * @return
     */
    @ResponseBody
    @ApiOperation("查询工资")
    @RequestMapping(value = "/salary/search/{id}",method = RequestMethod.GET)
    public CommonResult salarySearch(@PathVariable Integer id){
        SalaryVO salaryVO = salaryService.findVOById(id);
        CommonResult commonResult = CommonResult.success(salaryVO);
        return commonResult;
    }
}
