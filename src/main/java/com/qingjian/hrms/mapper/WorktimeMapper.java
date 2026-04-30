package com.qingjian.hrms.mapper;


import java.util.List;

import com.qingjian.hrms.entity.Worktime;
import com.qingjian.hrms.qo.WorktimeQO;
import com.qingjian.hrms.vo.WorktimeVO;
import org.springframework.stereotype.Repository;

/**
 * 考勤表 WorktimeMapper
 * @author qingjian
 * @date 2021-05-08 10:17:19
 */
@Repository
public interface WorktimeMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Worktime record);

    int insertSelective(Worktime record);


    Worktime selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Worktime record);

    int updateByPrimaryKey(Worktime record);

    List<WorktimeVO> list(WorktimeQO worktime);
}

