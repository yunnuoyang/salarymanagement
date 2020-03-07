package com.joinx.salary.controller;

import com.joinx.salary.pojo.AreaTimeVO;
import com.joinx.salary.pojo.Salary;
import com.joinx.salary.pojo.User;
import com.joinx.salary.service.DepartmentService;
import com.joinx.salary.service.SalaryService;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private SalaryService salaryService;

    @Autowired
    private UserService userService ;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/testCaculate")
    public String testCaculate(AreaTimeVO areaTimeVO){
        PageHelper<User> helper = new PageHelper<>();
        helper.setParam(new User());
        List<User> users = userService.userInfoByCondition(helper);
        for(User u:users){
            salaryService.calculateSalary(u.getUserNo(),new Salary(),areaTimeVO);
        }
        //将离职的员工的信息进行备份并从员工表移除
        List<String> unEmp = users.stream()
                .filter(user -> user.getStatus().equalsIgnoreCase("0"))
                .map(User::getUserNo).collect(Collectors.toList());

        userService.removeByUserNos(unEmp);

        return "测试成功";
    }

    @RequestMapping("depart")
    public String depart(AreaTimeVO areaTimeVO){
        departmentService.startCaculate(areaTimeVO);
        return "下发工资完成统计";
    }
}
