package com.qingjian.hrms.service.impl;

import com.qingjian.hrms.entity.Employee;
import com.qingjian.hrms.mapper.EmployeeMapper;
import com.qingjian.hrms.qo.EmployeeQO;
import com.qingjian.hrms.service.EmployeeService;
import com.qingjian.hrms.vo.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<EmployeeVO> list(EmployeeQO employee) {
        return employeeMapper.selectByEmployee(employee);
    }

    @Override
    public int add(Employee employee) {
        return employeeMapper.insert(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int edit(Employee employee) {
        return employeeMapper.updateByPrimaryKey(employee);
    }

    @Override
    public void deleteById(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EmployeeVO> findByEmployee(EmployeeQO employeeQO) {
        return employeeMapper.selectByEmployee(employeeQO);
    }
}
