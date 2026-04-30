package com.qingjian.hrms.service.impl;

import com.qingjian.hrms.entity.Salary;
import com.qingjian.hrms.mapper.SalaryMapper;
import com.qingjian.hrms.qo.SalaryQO;
import com.qingjian.hrms.service.SalaryService;
import com.qingjian.hrms.vo.SalaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryServiceImpl implements SalaryService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Override
    public List<SalaryVO> list(SalaryQO salaryQO) {
        return salaryMapper.list(salaryQO);
    }

    @Override
    public int add(Salary salary) {
        return salaryMapper.insert(salary);
    }

    @Override
    public int edit(Salary salary) {
        return salaryMapper.updateByPrimaryKey(salary);
    }

    @Override
    public SalaryVO findVOById(Integer id) {
        return salaryMapper.selectVOById(id);
    }
}
