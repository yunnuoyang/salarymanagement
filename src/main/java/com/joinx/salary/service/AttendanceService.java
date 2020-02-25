package com.joinx.salary.service;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Attendance;
import com.joinx.salary.util.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttendanceService {
   List<Attendance> attendancesInfo(PageHelper<Attendance> pageHelper);
}
