package com.joinx.salary.work;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Salary;
import com.joinx.salary.pojo.User;
import com.joinx.salary.service.SalaryService;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 每隔七天进行一次考勤任务
 */
@Component
public class WageWork {
   @Autowired
   private SalaryService salaryService;

   @Autowired
   private UserService userService;


   /*
   每周星期天一点进行一次考勤排查，将考勤信息分发给用户
    */
   @Scheduled(cron="0 0 1 ? * L")
   public String notifyUser(){
      if(TimeUtil.contains()){
         PageHelper<User> helper = new PageHelper<>();
         helper.setParam(new User());
         List<User> users = userService.userInfoByCondition(helper);
         for(User u:users){
            salaryService.calculateSalary(u.getUserNo(),new Salary(),new AreaTimeVO());
         }
      }

      return "";
   }
}
