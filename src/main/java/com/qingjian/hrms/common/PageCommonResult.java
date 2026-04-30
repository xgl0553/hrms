package com.qingjian.hrms.common;

import java.util.List;

public class PageCommonResult extends CommonResult{
    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static  <T extends Object> PageCommonResult  success(List<T> data, long count){
        PageCommonResult pageCommonResult = new PageCommonResult();
        pageCommonResult.setCount(count);
        pageCommonResult.setCode(CommonCode.SUCCESS);
        pageCommonResult.setData(data);
        return pageCommonResult;
    }

    public PageCommonResult(){};
}
