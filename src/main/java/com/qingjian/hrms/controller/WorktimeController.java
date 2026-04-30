package com.qingjian.hrms.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qingjian.hrms.common.CommonResult;
import com.qingjian.hrms.common.PageCommonResult;
import com.qingjian.hrms.entity.Worktime;
import com.qingjian.hrms.qo.WorktimeQO;
import com.qingjian.hrms.service.WorktimeService;
import com.qingjian.hrms.vo.WorktimeVO;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class WorktimeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WorktimeService worktimeService;


    /**
     * 考勤查询
     * @return
     */
    @ResponseBody
    @ApiOperation("考勤查询")
    @RequestMapping(value = "/worktime/list",method = RequestMethod.POST)
    public PageCommonResult workList(@RequestBody WorktimeQO worktime){
        PageHelper.startPage(worktime.getPage(),worktime.getLimit());
        if(worktime.getCheckTime()!=null){
            Date checkTime = worktime.getCheckTime();
            Calendar checkCalender = Calendar.getInstance();
            checkCalender.setTime(checkTime);
            checkCalender.set(Calendar.HOUR,0);
            checkCalender.set(Calendar.MINUTE,0);
            checkCalender.set(Calendar.SECOND,0);
            Date startTime = checkCalender.getTime();
            checkCalender.add(Calendar.DAY_OF_YEAR,1);
            Date endTime = checkCalender.getTime();
            worktime.setCheckStartTime(startTime);
            worktime.setCheckEndTime(endTime);
        }
        List<WorktimeVO> worktimeVOList = worktimeService.list(worktime);
        PageInfo<WorktimeVO> pageInfo = new PageInfo(worktimeVOList);
        //返回结果
        PageCommonResult commonResult = PageCommonResult.success(pageInfo.getList(),pageInfo.getTotal());
        return commonResult;
    }
    /**
     * 模拟考勤
     * @return
     */
    @ResponseBody
    @ApiOperation("模拟考勤")
    @RequestMapping(value = "/worktime/imitateCheck",method = RequestMethod.POST)
    public CommonResult imitateCheck(@RequestBody Worktime worktime){
        worktimeService.imitateCheck(worktime);
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }
    /**
     * 修改考勤为正常状态
     * @return
     */
    @ResponseBody
    @ApiOperation("修改考勤为正常状态")
    @RequestMapping(value = "/work/changeStatus/{workId}",method = RequestMethod.GET)
    public CommonResult imitateCheck(@PathVariable Integer workId){
        worktimeService.changeStatus(workId);
        CommonResult commonResult = CommonResult.success();
        return commonResult;
    }

}
