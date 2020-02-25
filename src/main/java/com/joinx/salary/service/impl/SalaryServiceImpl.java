package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.*;
import com.joinx.salary.repository.*;
import com.joinx.salary.service.SalaryService;
import com.joinx.salary.util.NumberGenerator;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
@Service
public class SalaryServiceImpl implements SalaryService {
   @Autowired
   private AttendanceRepository attendanceRepository;
   @Autowired
   private LeaveRepository leaveRepository;
   @Autowired
   private StandardRepository standardRepository;

   @Autowired
   private SalaryStandardRespository salaryStandardRespository;

   @Autowired
   private SalaryRepository salaryRepository;
   @Override
   public void calculateSalary(String userNo, Salary salary, AreaTimeVO areaTime) {
      //默认获取当前用户上个月的工资结算情况
      areaTime.setStart(areaTime.getStart()==null?TimeUtil.getLastMonthBegin():areaTime.getStart());
      areaTime.setEnd(areaTime.getEnd()==null?TimeUtil.getLastMonthEnd():areaTime.getEnd());
      salary.setEno(userNo);
      //查询出当前月份的缺勤表记录，进行日考勤,默认获取上月份的缺勤记录
      double v = attendanceCheck(userNo, areaTime);
      //请假扣除的工资,小时
      double v1 = leaveCheck(userNo, areaTime);
      SalaryStandard standard = new SalaryStandard();
      standard.setUserNo(userNo);
      List<SalaryStandard> salaryStandards = salaryStandardRespository.queryAll(standard);
     //得到该员工的基础薪资信息，计算出日工资,获取该月份的除双休的该上班日期
      double v2 = salaryStandards.get(0).getSalaryBasic() /
              (TimeUtil.getDaysOfMonth(areaTime.getStart())-
                      TimeUtil.getMonthWeekDays(areaTime.getStart()));
      double decresMoney=(v2*v)+((v2/standardRepository.getStandard().getOnlineHours())*v1);
      Salary salary1 = new Salary();
      salary1.setEno(userNo);
      salary1.setBasic(new BigDecimal(salaryStandards.get(0).getSalaryBasic()));
      salary1.setSno(new NumberGenerator().generator());
      salary1.setPermitTime(TimeUtil.getCurrent());
      salary1.setWage(new BigDecimal(decresMoney));
      //全勤奖
      salary1.setPerformance(new BigDecimal(decresMoney==0?200:0));
      salaryRepository.save(salary1);

   }
   public List<Salary> getLastSalaryRecord(String userNo, AreaTimeVO areaTime){
      List<Salary> byAreaTime = salaryRepository.getByAreaTime(userNo, areaTime);
      return byAreaTime;
   }

    @Override
    public List<Salary> getList(String salaryno) {

        return salaryRepository.getList(salaryno);
    }

   @Override
   public void update(Salary salary) {
      salaryRepository.update(salary);
   }


   //请假期间的扣除的工资，按小时计算
   private double leaveCheck(String userNo, AreaTimeVO areaTimeVO) {
      //获取到指定月份的所有的请假记录
      List<Leave> leaves = leaveRepository.isExitThatDay(userNo, areaTimeVO.getStart(), areaTimeVO.getEnd());
      double endHours=0;//请假所欠工作的总时长
      for (Leave l:leaves){
//         //如果请假记录的终止时间>查询的终止时间，即本记录的起始时间在当前查询月份内，而终止时间在下一月
//         if(TimeUtil.compare(l.getEndTime(),areaTimeVO.getEnd())){
//            Date nextRecode = l.getEndTime();
//            l.setEndTime(areaTimeVO.getEnd());
//            leaveRepository.update(l);
//            l.setStartTime(areaTimeVO.getEnd());
//            l.setEndTime(nextRecode);
//            leaveRepository.save(l);
//         }
         //当前用户此次请假记录的总时间长度 2为请假的记录
         if(l.getOperateResult()==0||l.getOperateResult()==1){
            continue;
         }
         double hours = TimeUtil.calculateHour(l.getStartTime(), l.getEndTime());
         double arg1=hours%24;//请假总时长除天数所剩的小时数
         double arg2=standardRepository.getStandard().getWorkStartHour()/2;//半天的时长
         double cost;
         if(arg1==arg2){//工作的时长
             cost=TimeUtil.calculateDay(l.getStartTime(), l.getEndTime())*8+arg2;
         }
         cost=TimeUtil.calculateDay(l.getStartTime(), l.getEndTime())*8;
         endHours+=cost;
      }
      return endHours;

   }

   //缺勤的记录，算出应减少的工资,按天计算
   private double attendanceCheck(String userNo, AreaTimeVO areaTime) {
      PageHelper<Attendance> pageHelper=new PageHelper<>();
      Attendance attendance=new Attendance();
      attendance.setAttendanceStart(areaTime.getStart());
      attendance.setAttendanceEnd(areaTime.getEnd());
      attendance.setEno(userNo);
      pageHelper.setParam(attendance);
      List<Attendance> records=attendanceRepository.oncheck(pageHelper);

      double notAttandance = 0;
      //计算出员工缺勤情况下的所扣工资情况
      for(Attendance attend:records){
       int lateHour=TimeUtil.calculateHour(attend.getAttendanceStart(),attend.getAttendanceEnd());
       if(lateHour<standardRepository.getStandard().getOnlineHours()/2){
          //在职时间小于规定的1/2，即今天缺勤，扣掉一天的薪水
            notAttandance+=1;
       }

       if(standardRepository.getStandard().getOnlineHours()/2<lateHour
               &&lateHour<standardRepository.getStandard().getOnlineHours()){
          //如果迟到的时间差大于规定时间的1/2，小于在职时间 ,即今天
            notAttandance+=0.5;
       }
      }
      System.out.println(notAttandance);
      return notAttandance;
//      salary.setEno(userNo);
//      Salary time = salaryRepository.getByAreaTime(salary);
//      double notSalary=(notAttandance/standardRepository.getStandard().getOnlineDays())*time.getBasic().doubleValue();
      //将该员工所扣的工资存入工资表中，
//      salary.setWage(new BigDecimal(notSalary));
//      salary.setEno(userNo);
//      salary.setSno(new NumberGenerator().generator());
//      salaryRepository.update(salary);

   }
}
