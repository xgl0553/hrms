package com.qingjian.hrms.mapper;

import java.util.List;

import com.qingjian.hrms.entity.Department;
import com.qingjian.hrms.vo.DepartmentVO;
import org.springframework.stereotype.Repository;

/**
 * 部门表 DepartmentMapper
 * @author qingjian
 * @date 2021-05-08 10:09:35
 */
@Repository
public interface DepartmentMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);



    Department selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<DepartmentVO> list(Department dept);
}
