package com.joinx.salary.service;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Salary;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SalaryService {
   void calculateSalary(String userNo, Salary salary, AreaTimeVO areaTime);
   List<Salary> getLastSalaryRecord(String userNo, AreaTimeVO areaTime);

    List<Salary> getList(String salaryno);

    void update(Salary salary);
}
