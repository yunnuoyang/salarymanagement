package com.joinx.salary.repository;

import com.joinx.salary.pojo.Department;
import com.joinx.salary.util.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DepartmentRepository {
   
   List<Department> getDepartments(PageHelper<Department> pageHelper);

    Department getDepartmentByDno(@Param("dno") String departmentNo);

    Department getByNames( @Param("departName")String departName);
}
