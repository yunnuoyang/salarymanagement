package com.joinx.salary.repository;

import com.joinx.salary.pojo.Salary;
import com.joinx.salary.pojo.SalaryStandard;
import com.joinx.salary.pojo.Standard;
import org.springframework.stereotype.Repository;

/**
 * @Author
 * @Date
 * @Description
 */
@Repository
public interface StandardRepository {
   Standard getStandard();

    void update(Standard standard);
}
