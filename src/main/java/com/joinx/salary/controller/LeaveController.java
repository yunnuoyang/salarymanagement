package com.joinx.salary.controller;

import com.joinx.salary.pojo.Attendance;
import com.joinx.salary.pojo.Leave;
import com.joinx.salary.pojo.User;
import com.joinx.salary.service.LeaveService;
import com.joinx.salary.service.UserService;
import com.joinx.salary.util.DecorateMap;
import com.joinx.salary.util.NumberGenerator;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
public class LeaveController {
   @Autowired
   private LeaveService leaveService;

   @Autowired
   private UserService userService;
   
   @RequestMapping("/leaves")
   @ResponseBody
   public Map getDepartment(Leave leave,
                            @RequestParam(value = "currentPage",defaultValue ="1") int currentPage
          , @RequestParam(value = "pageSize",defaultValue ="10") int pageSize){
      System.out.println(leave+"=====");
      PageHelper<Leave> pageHelper=new PageHelper<>();
      pageHelper.setParam(leave);
      pageHelper.setCurrentPage(currentPage);
      pageHelper.setPageSize(pageSize);
      List<Leave> leaves = leaveService.attendancesInfo(pageHelper);
      pageHelper.setDataList(leaves);
      PageHelper<User> userParam = new PageHelper<>();
      User user=new User();
      user.setUserNo(leave.getEno());
      userParam.setParam(user);
      List<User> users = userService.userInfoByCondition(userParam);
      List<Map<String, Object>> data =new ArrayList();

      for (Leave l:leaves) {
         if(l.getOperateResult()==null||l.getOperateResult()==0){
            continue;//还未处理的请假记录暂不展示
         }
         DecorateMap element=new DecorateMap();

         element.put("lNo",l.getLno())
                .put("startTime",TimeUtil.getYMDHMS(l.getStartTime()))
                .put("endTime",l.getEndTime()==null?"":TimeUtil.getYMDHMS(l.getEndTime()))
                .put("realName",users.get(0).getRealName())
                .put("reason",l.getReason())
                .put("applyTime",TimeUtil.getYMDHMS(l.getLeaveApply()))
                .put("operateTime",TimeUtil.getYMDHMS(l.getOperateTime()))
                .put("operateResult",l.getOperateResult()==0?"待审核":(l.getOperateResult()==1?"不通过":"通过"));
         data.add(element);
      }
      Map<String, Object> map=new LinkedHashMap<>();
      map.put("status", 200);
      map.put("message", "");
      map.put("total",data.size());
      map.put("data",data);
      return map;
   }
   @RequestMapping("/allNoResultLeaves")
   @ResponseBody
   public Map allNoResultLeaves(Leave leave,
                            @RequestParam(value = "currentPage",defaultValue ="1") int currentPage
          , @RequestParam(value = "pageSize",defaultValue ="10") int pageSize){
      PageHelper<User> param = new PageHelper<>();
      param.setParam(new User());
      List<User> users = userService.userInfoByCondition(param);
   List<Map<String, Object>> data =new ArrayList();
      for (User u:users
           ) {
          System.out.println("u = " + u);
         List<Leave> leaves = leaveService.attendancesInfoByUno(u.getUserNo());
         if(leaves.size()==0){
             continue;
         }
         for (Leave l : leaves) {
            if (l.getOperateResult() == 2 || l.getOperateResult() == 1) {
               continue;//还未处理的请假记录暂不展示
            }
            DecorateMap element = new DecorateMap();

            element.put("lNo", l.getLno())
                    .put("startTime", TimeUtil.getYMDHMS(l.getStartTime()))
                    .put("endTime", l.getEndTime() == null ? "" : TimeUtil.getYMDHMS(l.getEndTime()))
                    .put("realName", users.get(0).getRealName())
                    .put("reason", l.getReason())
                    .put("applyTime", TimeUtil.getYMDHMS(l.getLeaveApply()))
                    .put("operateResult", l.getOperateResult() == 0 ? "待审核" : (l.getOperateResult() == 1 ? "不通过" : "通过"));
            data.add(element);
         }
      }


      Map<String, Object> map=new LinkedHashMap<>();
      map.put("status", 200);
      map.put("message", "");
      map.put("total",data.size());
      map.put("data",data);
      return map;
   }
   @RequestMapping("/apply")
   @ResponseBody
   public String apply(Leave leave, HttpSession session){
      System.out.println("leave = " + leave);
      User loginUser = (User) session.getAttribute("loginUser");
      leave.setLeaveApply(TimeUtil.getCurrent());
      leave.setEno(loginUser.getUserNo());
      leave.setLno(new NumberGenerator().generator());
      leaveService.applyLeave(leave);
      return "申请已经提交";
   }
   @RequestMapping("/leave/operate")
   @ResponseBody
   public String operate(Leave leave){
       System.out.println(leave);
      leave.setOperateTime(TimeUtil.getCurrent());
      leaveService.operate(leave);
      return "已批复";
   }

}
