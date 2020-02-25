package com.joinx.salary.controller;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Salary;
import com.joinx.salary.pojo.SalaryOperate;
import com.joinx.salary.pojo.User;
import com.joinx.salary.service.SalaryOperateService;
import com.joinx.salary.service.SalaryService;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.DecorateMap;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @Author
 * @Date
 * @Description
 */
@Controller
public class SalaryController {
   @Autowired
   private SalaryService salaryService;
   @Autowired
   private UserService userService;

   @Autowired
   private SalaryOperateService operateService;

   @RequestMapping("/salary/gather")
   @ResponseBody
   public List calculateSalary(String userNo ,  AreaTimeVO areaTime){
      List<Salary> money=salaryService.getLastSalaryRecord(userNo,areaTime);
      double basic=0;
      double performance=0;
      double wage=0;
      for (Salary s: money
           ) {
            basic+=s.getBasic().doubleValue();
            performance+=s.getPerformance().doubleValue();
            wage+=s.getWage().doubleValue();
      }
      DecorateMap map = new DecorateMap();
      List data=new ArrayList();
      map.put("value",basic)
         .put("name","基础薪资");
      data.add(map);
      DecorateMap map2 = new DecorateMap();
      map2.put("value",performance)
          .put("name","绩效奖金");
      data.add(map2);
      DecorateMap map3 = new DecorateMap();
      map3.put("value",wage)
          .put("name","缺勤扣除金额");
      data.add(map3);
      return data;
   }
   @RequestMapping("/salary/list")
   @ResponseBody
   public Map list(String userNo, AreaTimeVO areaTime, HttpSession session){
      User loginUser = (User) session.getAttribute("loginUser");
      if (loginUser.getRole().getRno().equalsIgnoreCase("000002")){
         areaTime.setStart(areaTime.getStart()==null?TimeUtil.getLastMonthBegin():areaTime.getStart());
         areaTime.setEnd(areaTime.getEnd()==null?TimeUtil.getLastMonthEnd():areaTime.getEnd());
      }
      List<Salary> salaries = salaryService.getLastSalaryRecord(userNo, areaTime);
      List data=new ArrayList();
      for (Salary s:salaries
           ) {
         User user=userService.userInfoByUno(s.getEno());
         Map map = new DecorateMap()
                 .put("wage", s.getWage())
                 .put("performance", s.getPerformance())
                 .put("basic", s.getBasic())
                 .put("permitTime",TimeUtil.getYMDHMS(s.getPermitTime()))
                 .put("salaryNo",s.getSno())
                 .put("userNo",s.getEno())
                 .put("loginName", user.getLoginName())
                 .put("realName", user.getRealName());
         data.add(map);
      }

      return  new DecorateMap()
              .put("status", 200)
              .put("message", "")
              .put("total",data.size())
              .put("data",data);
   }
   @RequestMapping("/salary/operate")
   @ResponseBody
   public Map operate(String userNo, AreaTimeVO areaTime, HttpSession session){
      List<SalaryOperate> operates=operateService.queryAll();
       List data=new ArrayList();
      for (SalaryOperate operate:operates
            ) {
           List<Salary> salaries=salaryService.getList(operate.getSalaryno());
           for(Salary s:salaries){
               User user=userService.userInfoByUno(s.getEno());
               Map map = new DecorateMap()
                       .put("wage", s.getWage())
                       .put("performance", s.getPerformance())
                       .put("basic", s.getBasic())
                       .put("permitTime",TimeUtil.getYMDHMS(s.getPermitTime()))
                       .put("salaryNo",s.getSno())
                       .put("userNo",s.getEno())
                       .put("loginName", user.getLoginName())
                       .put("id",operate.getId() )
                       .put("status", operate.getStatus().equals(0)?
                       "未处理":"已处理")
                       .put("realName", user.getRealName());
               data.add(map);
           }
       }

      return  new DecorateMap()
              .put("status", 200)
              .put("message", "")
              .put("total",data.size())
              .put("data",data);
   }

   @RequestMapping("/salary/resure")
    @ResponseBody
    public String resure(SalaryOperate operate){
       int num=operateService.queryBySno(operate);
       if (num >0) {
           return "等待处理中，请勿再次提交";
       }
       SalaryOperate insert = operateService.insert(operate);
       return "提交成功,等待处理中";

   }

   @RequestMapping("/salary/update")
    @ResponseBody
    public String updata(Salary salary,SalaryOperate operate){
       if(operate.getStatus().equals(0)){
           return "已经处理过";
       }
       salaryService.update(salary);
       operate.setSalaryno(salary.getSno());
       operate.setStatus(1);
       operateService.update(operate);
       return "已处理";
   }

}
