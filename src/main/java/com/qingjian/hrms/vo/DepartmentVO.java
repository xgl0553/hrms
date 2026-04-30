package com.qingjian.hrms.vo;

import com.qingjian.hrms.entity.Employee;
import io.swagger.annotations.ApiModelProperty;

public class DepartmentVO {
    @ApiModelProperty(value = "")
    private Integer id;

    @ApiModelProperty(value = "部门名称")
    private String deptname;

    @ApiModelProperty(value = "负责人")
    private Employee chargeman;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public Employee getChargeman() {
        return chargeman;
    }

    public void setChargeman(Employee chargeman) {
        this.chargeman = chargeman;
    }
}
