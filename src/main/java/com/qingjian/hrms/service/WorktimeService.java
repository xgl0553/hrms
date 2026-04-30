package com.qingjian.hrms.service;

import com.qingjian.hrms.entity.Worktime;
import com.qingjian.hrms.qo.WorktimeQO;
import com.qingjian.hrms.vo.WorktimeVO;

import java.util.List;

public interface WorktimeService {
    List<WorktimeVO> list(WorktimeQO worktime);

    int imitateCheck(Worktime worktime);

    void changeStatus(Integer workId);
}
