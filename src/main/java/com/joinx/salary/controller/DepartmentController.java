package com.joinx.salary.controller;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Department;
import com.joinx.salary.pojo.Salary;
import com.joinx.salary.service.DepartmentService;
import com.joinx.salary.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author
 * @Date
 * @Description
 */
@Controller
public class DepartmentController {
   
   @Autowired
   private DepartmentService departmentService;
   
   @RequestMapping("/department/info")
   @ResponseBody
   public Map getDepartment(Department department,
                            @RequestParam(value = "currentPage",defaultValue ="1") int currentPage
          , @RequestParam(value = "pageSize",defaultValue ="10") int pageSize){
      PageHelper<Department> pageHelper=new PageHelper<>();
      pageHelper.setParam(department);
      pageHelper.setCurrentPage(currentPage);
      pageHelper.setPageSize(pageSize);
      List<Department> departments = departmentService.departmentsInfo(pageHelper);
      pageHelper.setDataList(departments);
      Map<String, Object> map=new LinkedHashMap<>();
      map.put("status", 200);
      map.put("message", "");
      map.put("total",departments.size());
      map.put("data",departments);
      return map;
   }

   @RequestMapping("/depart/charts")
   @ResponseBody
   public Map salaryCharts(AreaTimeVO areaTimeVO){
      Map map =departmentService.getCharts(areaTimeVO);
      return map;
   }

    @RequestMapping("/depart/allAspect")
   @ResponseBody
   public List allAspect(AreaTimeVO areaTimeVO,
            String departName){
        System.out.println("areaTimeVO = " + areaTimeVO);
        System.out.println("departName = " + departName);
       List list=departmentService.aspect(areaTimeVO,departName);

      return list;
   }


}
