package com.qingjian.hrms.mapper;


import java.util.List;

import com.qingjian.hrms.entity.Employee;
import com.qingjian.hrms.qo.EmployeeQO;
import com.qingjian.hrms.vo.EmployeeVO;
import org.springframework.stereotype.Repository;

/**
 * 员工表 EmployeeMapper
 * @author qingjian
 * @date 2021-04-28 10:27:40
 */
@Repository
public interface EmployeeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    List<EmployeeVO> selectByEmployee(EmployeeQO employee);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

}
