package com.qingjian.hrms.service.impl;

import com.qingjian.hrms.entity.Department;
import com.qingjian.hrms.mapper.DepartmentMapper;
import com.qingjian.hrms.service.DepartmentService;
import com.qingjian.hrms.vo.DepartmentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<DepartmentVO> list(Department dept) {
        return departmentMapper.list(dept);
    }

    @Override
    public int add(Department dept) {
        if(dept.getId() !=null){
            return departmentMapper.updateByPrimaryKey(dept);
        }else{
            return departmentMapper.insert(dept);
        }
    }

    @Override
    public Department findById(Integer deptId) {
        return departmentMapper.selectByPrimaryKey(deptId);
    }
}
