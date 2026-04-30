package com.qingjian.hrms.mapper;

import java.util.List;

import com.qingjian.hrms.entity.Salary;
import com.qingjian.hrms.qo.SalaryQO;
import com.qingjian.hrms.vo.SalaryVO;
import org.springframework.stereotype.Repository;

/**
 * 工资表 SalaryMapper
 * @author qingjian
 * @date 2021-05-08 10:25:52
 */
@Repository
public interface SalaryMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Salary record);

    int insertSelective(Salary record);


    Salary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Salary record);

    int updateByPrimaryKey(Salary record);

    List<SalaryVO> list(SalaryQO salaryQO);

    SalaryVO selectVOById(Integer id);
}
