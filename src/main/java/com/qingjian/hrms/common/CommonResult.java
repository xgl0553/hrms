package com.qingjian.hrms.common;

import java.util.List;

public class CommonResult {
    public int code;
    public String msg;
    public Object data;

    public CommonResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public CommonResult() {
    }

    public static <T extends Object> CommonResult success(List<T> list) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(CommonCode.SUCCESS);
        commonResult.setData(list);
        return commonResult;
    }
    public static <T extends Object> CommonResult success(Object data) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(CommonCode.SUCCESS);
        commonResult.setData(data);
        return commonResult;
    }
    public static <T extends Object> CommonResult success() {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(CommonCode.SUCCESS);
        return commonResult;
    }

    public static CommonResult fail(String msg) {
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(CommonCode.FAIL);
        commonResult.setMsg(msg);
        return commonResult;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
