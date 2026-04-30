package com.qingjian.hrms.service;

import com.qingjian.hrms.entity.Salary;
import com.qingjian.hrms.qo.SalaryQO;
import com.qingjian.hrms.vo.SalaryVO;

import java.util.List;

public interface SalaryService {
    List<SalaryVO> list(SalaryQO salaryQO);

    int add(Salary salary);

    int edit(Salary salary);

    SalaryVO findVOById(Integer id);
}
