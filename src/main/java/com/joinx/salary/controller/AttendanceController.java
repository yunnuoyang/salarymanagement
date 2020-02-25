package com.joinx.salary.controller;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Attendance;
import com.joinx.salary.pojo.Department;
import com.joinx.salary.pojo.Leave;
import com.joinx.salary.service.AttendanceService;
import com.joinx.salary.service.DepartmentService;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date
 * @Description
 */
@Controller
public class AttendanceController {
   
   @Autowired
   private AttendanceService attendanceService;
   
   @RequestMapping("/attendances")
   @ResponseBody
   public Map<String, Object> getDepartment(Attendance attendance,
          @RequestParam(value = "currentPage",defaultValue ="1") int currentPage
          ,@RequestParam(value = "pageSize",defaultValue ="10") int pageSize){
      PageHelper<Attendance> pageHelper=new PageHelper<>();
      pageHelper.setParam(attendance);
      pageHelper.setCurrentPage(currentPage);
      pageHelper.setPageSize(pageSize);
      List<Attendance> attendances = attendanceService.attendancesInfo(pageHelper);
      pageHelper.setDataList(attendances);

      List<Map<String, Object>> data =new ArrayList();
      for (Attendance attend:attendances) {
//         data.put("userNo",l.getEno());
         Map element=new LinkedHashMap();
         element.put("ANo",attend.getNo());
         element.put("start",attend.getAttendanceStart());
         element.put("end",attend.getAttendanceEnd());
         data.add(element);
      }
      Map<String, Object> map=new LinkedHashMap<>();
      map.put("status", 200);
      map.put("message", "");
      map.put("total",data.size());
      map.put("data",data);
      return map;
   }

   
}
