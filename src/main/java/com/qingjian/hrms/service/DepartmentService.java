package com.qingjian.hrms.service;

import com.qingjian.hrms.entity.Department;
import com.qingjian.hrms.vo.DepartmentVO;

import java.util.List;

public interface DepartmentService {
    List<DepartmentVO> list(Department dept);

    int add(Department dept);

    Department findById(Integer deptId);
}
