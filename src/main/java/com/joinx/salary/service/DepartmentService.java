package com.joinx.salary.service;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Department;
import com.joinx.salary.pojo.Salary;
import com.joinx.salary.util.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface DepartmentService {
   List<Department> departmentsInfo(PageHelper<Department> pageHelper);

    Map getCharts(AreaTimeVO areaTime);

    List aspect(AreaTimeVO areaTimeVO,String departName);

    Map<String,Object> record(AreaTimeVO areaTimeVO);
}
