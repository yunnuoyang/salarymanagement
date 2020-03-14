package com.joinx.salary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.joinx.salary.pojo.*;
import com.joinx.salary.service.DepartmentService;
import com.joinx.salary.service.SalaryStandardService;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.JsonUtil;
import com.joinx.salary.util.NumberGenerator;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * @Author
 * @Date
 * @Description
 */
@Controller
public class UserController {
   private Logger logger=  LoggerFactory.getLogger(UserController.class);
   @Autowired
   private UserService userService;
   @Autowired
   private DepartmentService departmentService;
   @Autowired
   private PasswordEncoder passwordEncoder;

   @Autowired
   private SalaryStandardService salaryStandardService;



   @RequestMapping("/user/delete")
   @ResponseBody
    public void delete(String userNo) throws IOException {
       System.out.println("删除用户"+userNo);
        userService.deleteUser(userNo);
     }


   /**
    * 查询用户的集合信息
    * @return
    * @throws JsonProcessingException
    */
   @RequestMapping("/user/info")
   @ResponseBody
   public String tableInfo() throws JsonProcessingException {
      Map<String, Object> map=userService.collectAllInfo();
      String json = JsonUtil.objToJson(map);
      System.out.println(json);
      return json;
   }
   @RequestMapping("/user/update")
   @ResponseBody
   public ResponseDto update(User user,Double basicSalary){
      System.out.println(user);
      if(basicSalary!=null){
         SalaryStandard standard = new SalaryStandard();
         standard.setSalaryBasic(basicSalary);
         standard.setUserNo(user.getUserNo());
         salaryStandardService.update(standard);
      }
      int num=userService.update(user);
      if(num>0){
         return new ResponseDto(0,"修改成功");
      }else
         return new ResponseDto(1,"修改失败");
   }
   @RequestMapping("/user/pwd")
   @ResponseBody
   public ResponseDto updatePwd(String oldPwd,String newPwd,String rePwd,User user){
      System.out.println(oldPwd+newPwd+rePwd);
      if(oldPwd==null){
         return new ResponseDto(0,"原密码不能为空");
      }
      if(newPwd==null){
         return new ResponseDto(1,"新密码不能为空");
      }
      if(!newPwd.equalsIgnoreCase(rePwd)){
         return new ResponseDto(2,"新密码不一致");
      }
      PageHelper<User> userPageHelper = new PageHelper<>();
      userPageHelper.setParam(user);
      List<User> users = userService.userInfoByCondition(userPageHelper);
      if(users.size()>0){
         User user1 = users.get(0);
         if(!user1.getPassword().equalsIgnoreCase(passwordEncoder.encode(oldPwd))){
            return new ResponseDto(3,"用户原密码错误");
         }
      }
         user.setPassword(passwordEncoder.encode(newPwd));
         userService.update(user);
         return new ResponseDto(5,"修改密码成功");

   }


   @RequestMapping("/layer/{baseForm}")
   public String layerForm(@PathVariable String baseForm){
      System.out.println("到达layer"+baseForm);
      return "layer/"+baseForm;
   }
   //当前用户的基本信息
   @RequestMapping("/layer/baseinfo")
   @ResponseBody
   public List<Object> baseInfo(HttpSession session){
      //获取当前在线的本机的用户的session
      User loginUser = (User) session.getAttribute("loginUser");
//      //将用户的部门查询出来
//      Department param=new Department();
//             param.setDno(loginUser.getDepartmentNo());
//      PageHelper<Department> pageHelper = new PageHelper<>();
//      pageHelper.setParam(param);
//      List<Department> departments = departmentService.departmentsInfo(pageHelper);
//         Department department=null;
//      if(departments.size()>0){
//         department=departments.get(0);
//      }
      //将所有的部门信息查询出来
      List<Department> info = departmentService.departmentsInfo(null);
      List<Object> baseinfo=new ArrayList<>();
      baseinfo.add(loginUser);
//      baseinfo.add(department);
      baseinfo.add(info);
      return baseinfo;
   }

   //当前用户的基本信息
   @RequestMapping("/baseinfoApp")
   @ResponseBody
   public User baseInfoApp(User user){
      System.out.println("user = " + user);
     User baseinfo= userService.getByUserNo(user);
      return baseinfo;
   }
   
   
   @RequestMapping("/userInfo")
   public List<User> userInfo(User user,
                              @RequestParam(value = "currentPage",defaultValue ="1") int currentPage
                              ,@RequestParam(value = "pageSize",defaultValue ="10") int pageSize
   ){
      logger.info("userInfo....开始查询用户的信息");
      System.out.println("userInfo....");
      PageHelper<User> pageHelper=new PageHelper<>();
      pageHelper.setParam(user);
      pageHelper.setCurrentPage(currentPage);
      pageHelper.setPageSize(pageSize);
      List<User> userList=userService.userInfoByCondition(pageHelper);
      pageHelper.setDataList(userList);
      pageHelper.getDataList();
      int totalPage = pageHelper.getTotalPage();
      int startIndex = pageHelper.getStartIndex();
      System.out.println(totalPage+"===="+startIndex);
      return userList;
   }
   @RequestMapping("/storeInfo")
   @ResponseBody
   public String storeInfo(User user,String roleId,Double basic){
      logger.info("storeInfo....开始保存用户的信息");
      User us= userService.getUserByLoginName(user.getLoginName());
      if(us!=null){
         return "该登录名已被占用，请重新输入";
      }
      System.out.println("storeInfo....开始保存用户的信息"+user);
      user.setWorkAge(user.getWorkAge()==null?0:user.getWorkAge());
      user.setStatus("1");
      user.setUserNo(new NumberGenerator().generator());
      user.setPassword(passwordEncoder.encode(passwordEncoder.getDefaultPassword()));
      userService.saveUser(user,(roleId==""||roleId==null)?"000002":roleId);

     //基本工资表
      SalaryStandard standard = new SalaryStandard();
      standard.setUserNo(user.getUserNo());
      standard.setSalaryBasic(basic);
      salaryStandardService.insert(standard);
      return "添加用户成功";
   }
   @RequestMapping("/user/check")
   @ResponseBody
   public String distingLoginName(String loginName) {
     User us= userService.getUserByLoginName(loginName);
     if(us!=null){
        return "该登录名已被占用，请重新输入";
     }
     return "";
   }
   
}
