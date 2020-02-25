package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.Leave;
import com.joinx.salary.pojo.SalaryDay;
import com.joinx.salary.repository.LeaveRepository;
import com.joinx.salary.service.LeaveService;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
@Service
public class LeaveServiceImpl implements LeaveService {
   @Autowired
   private LeaveRepository leaveRepository;

   @Autowired
   private SalaryDay salaryDay;
   @Override
   public List<Leave> attendancesInfo(PageHelper<Leave> pageHelper) {
      return leaveRepository.leaves(pageHelper);
   }

   @Override
   public void applyLeave(Leave leave) {
      //如果请假记录的终止时间>查询的终止时间，即本记录的起始时间在当前查询月份内，而终止时间在下一月
         if(TimeUtil.compare(leave.getEndTime(),salaryDay.getCaculateDay())
                 &&!TimeUtil.compare(leave.getStartTime(),salaryDay.getCaculateDay())){
            Date nextRecode =leave.getEndTime();
            leave.setEndTime(salaryDay.getCaculateDay());
            leaveRepository.save(leave);
            leave.setStartTime(salaryDay.getCaculateDay());
            leave.setEndTime(nextRecode);
            leaveRepository.save(leave);
         }else{
             leaveRepository.save(leave);
         }
   }

    @Override
    public List<Leave> attendancesInfoByUno(String userNo) {
        return leaveRepository.list(userNo);
    }

    @Override
    public void operate(Leave leave) {
        leaveRepository.updateByLno(leave);
    }
}
