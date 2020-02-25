package com.joinx.salary.repository;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Attendance;
import com.joinx.salary.util.PageHelper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AttendanceRepository {
   
   List<Attendance> oncheck(PageHelper<Attendance> pageHelper);
}
