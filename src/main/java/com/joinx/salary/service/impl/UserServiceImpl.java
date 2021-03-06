package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.Department;
import com.joinx.salary.pojo.Role;
import com.joinx.salary.pojo.SalaryStandard;
import com.joinx.salary.pojo.User;
import com.joinx.salary.repository.DepartmentRepository;
import com.joinx.salary.repository.RoleRepository;
import com.joinx.salary.repository.SalaryStandardRespository;
import com.joinx.salary.repository.UserRepository;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author
 * @Date
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private RoleRepository roleRepository;

   @Autowired
   private DepartmentRepository departmentRepository;

   @Autowired
   private SalaryStandardRespository salaryStandardRespository;
   @Override
   public User loginUser(User user) {
      User loginUser = userRepository.loginUser(user);
      return loginUser;
   }
   
   @Override
   public List<User> userInfoByCondition(PageHelper<User> pageHelper) {
      pageHelper.setTotalPage(userRepository.count());
      System.out.println(pageHelper.getTotalPage());
      return userRepository.getUsers(pageHelper);
   }
   
   @Override
   public void saveUser(User user, String roleId) {
      userRepository.save(user);
      
      roleRepository.saveToURTable(user.getUserNo(),roleId);
   }

   @Override
   public int update(User user) {
      return userRepository.update(user);
   }

   @Override
   public Map<String, Object> collectAllInfo() {
      Map<String, Object> map=new LinkedHashMap<>();
      map.put("status", 200);
      map.put("message", "");

      //
      PageHelper<User> pageHelper = new PageHelper<>();
      pageHelper.setParam(new User());
      List<User> users = userRepository.getUsers(pageHelper);
      List<Map<String, Object>> data =new ArrayList();
      for (User u: users) {
         Role role = roleRepository.getRoleByUserNo(u.getUserNo());
         Department department=departmentRepository.getDepartmentByDno(u.getDepartmentNo());
         Map<String, Object> element=new LinkedHashMap<>();
         if(role.getRno().equalsIgnoreCase("000001")){
            //不展示管理员的信息
            continue;
         }
         SalaryStandard standard = new SalaryStandard();
         standard.setUserNo(u.getUserNo());
         SalaryStandard standard1 = salaryStandardRespository.queryAll(standard).get(0);
         element.put("basicSalary",standard1.getSalaryBasic());
         element.put("userNo",u.getUserNo());
         element.put("loginName",u.getLoginName());
         element.put("realName",u.getRealName());
         element.put("status",u.getStatus());
         element.put("address",u.getAddress());
         element.put("tel",u.getTel());
         element.put("sex",u.getSex());
         element.put("email",u.getEmail());
         element.put("workAge",u.getWorkAge());
         element.put("roleName",role.getName());
         element.put("departmentNo",department.getDno());
         element.put("departmentName",department.getDName());
         data.add(element);
      }
      map.put("total",data.size());
      map.put("data",data);
      return map;
   }

   @Override
   public void deleteUser(String userNo) {
         userRepository.delete(userNo);
         roleRepository.delete(userNo);
   }

   @Override
   public User userInfoByUno(String eno) {

      return userRepository.userInfoByUno(eno);
   }

   @Override
   public User getUserByLoginName(String loginName) {
      return userRepository.getUserByLoginName(loginName);
   }

   @Override
   public void removeByUserNos() {
      List<User> users = userRepository.getUsers(new User());
      //将离职的员工的信息进行备份并从员工表移除
      List<String> unEmp = users.stream()
              .filter(user -> user.getStatus().equalsIgnoreCase("0"))
              .map(User::getUserNo).collect(Collectors.toList());

      //将这些id的员工存储到备份表中

      //将这些user_no的员工
      userRepository.removeByUserNos(unEmp);
      //删除用户的身份信息
      roleRepository.removeUserRoleByUserNos(unEmp);
      //删除员工工资信息
      salaryStandardRespository.removeByUserNos(unEmp);
   }

   @Override
   public User getByUserNo(User user) {
      return userRepository.userInfoByUno(user.getUserNo());
   }
}
