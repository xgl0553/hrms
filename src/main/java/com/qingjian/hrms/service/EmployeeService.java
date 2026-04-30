package com.qingjian.hrms.service;

import com.qingjian.hrms.entity.Employee;
import com.qingjian.hrms.qo.EmployeeQO;
import com.qingjian.hrms.vo.EmployeeVO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeVO> list(EmployeeQO employee);

    int add(Employee employee);

    Employee findById(Integer id);

    int edit(Employee employee);

    void deleteById(Integer id);

    List<EmployeeVO> findByEmployee(EmployeeQO employeeQO);
}
