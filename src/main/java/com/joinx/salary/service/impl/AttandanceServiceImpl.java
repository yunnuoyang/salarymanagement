package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Attendance;
import com.joinx.salary.repository.AttendanceRepository;
import com.joinx.salary.service.AttendanceService;
import com.joinx.salary.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
@Service
public class AttandanceServiceImpl implements AttendanceService {
   @Autowired
   private AttendanceRepository attendanceRepository;
   @Override
   public List<Attendance> attendancesInfo(PageHelper<Attendance> pageHelper) {
      return attendanceRepository.oncheck(pageHelper);
   }
}
