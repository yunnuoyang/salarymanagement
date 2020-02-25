package com.joinx.salary.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joinx.salary.config.SessionFilter;
import com.joinx.salary.pojo.ResponseDto;
import com.joinx.salary.pojo.Role;
import com.joinx.salary.pojo.User;
import com.joinx.salary.repository.RoleRepository;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.CheckStatus;
import com.joinx.salary.util.JsonUtil;
import com.joinx.salary.util.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 登入登出控制器
 */
@Controller
@RequestMapping("/login")
public class LoginController {
   private Logger logger = LoggerFactory.getLogger(SessionFilter.class);
   @Autowired
   private UserService userService;
   @Autowired
   private RoleRepository roleRepository;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @RequestMapping(value = "/login")
   public String loginUrl(){
      System.out.println("跳转到登陆界面");
      return "login/login";
   }
   @RequestMapping(value = "/main")
   public String mainUrl(){
      System.out.println("跳转到main界面");
      return "main/main";
   }
   @RequestMapping(value="/dologin")
   @ResponseBody
   public ResponseDto loginProcess(User user, HttpSession session ) throws JsonProcessingException {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      System.out.println("登陆处理中");
      System.out.println(user);
      User loginUser = userService.loginUser(user);
      Role role = null;
      //存在此员工的账号并且是在职状态
      if(loginUser==null){
         return new ResponseDto(1,"当前无此用户",null);
      }
      if(!loginUser.getStatus().equals(CheckStatus.Entrant.getStatus())){
         return new ResponseDto(2,"当前用户已经注销",null);
      }else {
         role=roleRepository.getRoleByUserNo(loginUser.getUserNo());
         loginUser.setRole(role);
         session.setMaxInactiveInterval(60*30);
         session.setAttribute("loginUser",loginUser);
         System.out.println(loginUser);
//         session.setAttribute("role",role);
         return new ResponseDto(3,"核对用户身份信息成功，登陆中...",loginUser);
      }
   }
   @RequestMapping("/logout")
   public String logout(HttpSession session){
      System.out.println("进行退出操作");
      logger.info("进行退出操作");
      session.removeAttribute("loginUser");
      session.invalidate();
      return "login/login";
   }


}
