package com.joinx.salary.repository;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Salary;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalaryRepository {
   void save(Salary salary);

   List<Salary> getByAreaTime(@Param("userNo") String userNo, @Param("area") AreaTimeVO areaTime);

    List<Salary> getList(@Param("sno") String salaryno);

    void update(Salary salary);

//   int update(Salary salary);
}
