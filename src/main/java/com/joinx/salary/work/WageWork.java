package com.joinx.salary.work;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Salary;
import com.joinx.salary.pojo.User;
import com.joinx.salary.service.DepartmentService;
import com.joinx.salary.service.SalaryService;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 每天进行一次任务，看是否为工资发放日
 */
@Component
public class WageWork {
    //离职员工的状态为0
    private final static String UNEMP="0";

   @Autowired
   private SalaryService salaryService;

   @Autowired
   private UserService userService;

   @Autowired
   private DepartmentService departmentService;


   /*
   每月5号九点30分启动此任务线程
    */
   @Scheduled(cron="0 30 9 5 * ? ")
   public String notifyUser(){

          System.out.println("当前工资发放日期---->TimeUtil.getYMDHMS(new Date(System.currentTimeMillis())) = " + TimeUtil.getYMDHMS(new Date(System.currentTimeMillis())));
          //发放工资
         PageHelper<User> helper = new PageHelper<>();
         helper.setParam(new User());
         List<User> users = userService.userInfoByCondition(helper);
         for(User u:users){
             //计算员工工资
            salaryService.calculateSalary(u.getUserNo(),new Salary(),new AreaTimeVO());
         }
         //计算部门工资
         departmentService.startCaculate(new AreaTimeVO());

         //将离职的员工的信息进行备份并从员工表移除
          List<String> unEmp = users.stream()
                  .filter(user -> user.getStatus().equalsIgnoreCase(UNEMP))
                  .map(User::getUserNo).collect(Collectors.toList());

          userService.removeByUserNos(unEmp);
      System.out.println("任务结束时间：" +TimeUtil.getYMDHMS(new Date()));

      //
      return "";
   }
//   @Scheduled(fixedRate = 5000)
//   public void reportCurrentTime() {
//      System.out.println("TimeUtil.getYMDHMS(new Date()) = " + TimeUtil.getYMDHMS(new Date()));
//   }
}
