package com.joinx.salary.pojo;

import java.io.Serializable;

/**
 * (SalaryOperate)实体类
 *
 * @author makejava
 * @since 2020-02-19 15:46:41
 */
public class SalaryOperate implements Serializable {
    private static final long serialVersionUID = -82211187881742506L;
    
    private Integer id;
    /**
    * 工资编号
    */
    private String salaryno;
    /**
    * 薪资状态1.处理后，0未处理
    */
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSalaryno() {
        return salaryno;
    }

    public void setSalaryno(String salaryno) {
        this.salaryno = salaryno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}