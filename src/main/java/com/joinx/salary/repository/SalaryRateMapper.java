package com.joinx.salary.repository;

import com.joinx.salary.pojo.SalaryRate;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;


public interface SalaryRateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SalaryRate record);

    int insertSelective(SalaryRate record);

    SalaryRate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SalaryRate record);

    int updateByPrimaryKey(SalaryRate record);

    List<SalaryRate> list();

}