package com.qingjian.hrms.service.impl;

import com.qingjian.hrms.entity.Worktime;
import com.qingjian.hrms.mapper.WorktimeMapper;
import com.qingjian.hrms.qo.WorktimeQO;
import com.qingjian.hrms.service.WorktimeService;
import com.qingjian.hrms.vo.WorktimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorktimeServiceImpl implements WorktimeService {
    @Autowired
    private WorktimeMapper worktimeMapper;


    @Override
    public List<WorktimeVO> list(WorktimeQO worktime) {
        return worktimeMapper.list(worktime);
    }

    @Override
    public int imitateCheck(Worktime worktime) {
        return worktimeMapper.insert(worktime);
    }

    @Override
    public void changeStatus(Integer workId) {
        Worktime worktime = worktimeMapper.selectByPrimaryKey(workId);
        worktime.setStatus(0);
        worktimeMapper.updateByPrimaryKey(worktime);
    }
}
