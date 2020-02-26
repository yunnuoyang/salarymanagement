package com.joinx.salary.service.impl;

import com.joinx.salary.pojo.*;
import com.joinx.salary.repository.*;
import com.joinx.salary.service.DepartmentService;
import com.joinx.salary.util.DecorateMap;
import com.joinx.salary.util.PageHelper;
import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author
 * @Date
 * @Description
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
   @Autowired
   private DepartmentRepository departmentRepository;
   @Autowired
   private UserRepository userRepository;
   @Autowired
   private SalaryRepository salaryRepository;

   @Autowired
   private DepartSalaryRecordMapper departSalaryRecordMapper;

   @Autowired
   private SalaryRateMapper salaryRateMapper;
   
   @Override
   public List<Department> departmentsInfo(PageHelper<Department> pageHelper) {
      return departmentRepository.getDepartments(pageHelper);
   }

   @Override
   public Map getCharts(AreaTimeVO areaTime) {
      HashMap<String, Object> maps = new HashMap<>();
      DecorateMap map = new DecorateMap();
      areaTime.setStart(areaTime.getStart()==null?TimeUtil.getLastMonthBegin():areaTime.getStart());
      areaTime.setEnd(areaTime.getEnd()==null?TimeUtil.getLastMonthEnd():areaTime.getEnd());
      List series=new ArrayList();
      List xAxis=new ArrayList();
      //获取所在的规定月份的工资
      DepartSalaryRecordExample e = new DepartSalaryRecordExample();
      e.createCriteria()
              .andSalarySendTimeBetween(areaTime.getStart(),areaTime.getEnd());
      List<DepartSalaryRecord> records = departSalaryRecordMapper.selectByExample(e);
      records=check(records,areaTime);


      for(DepartSalaryRecord record:records){
         series.add(record.getSalaryCount());
         //+
         //                 TimeUtil.getYMD(areaTime.getStart())+"至"+TimeUtil.getYMD(areaTime.getEnd())+"所缴纳总金额"
         xAxis.add(departmentRepository.getDepartmentByDno(record.getDepartId()).getDName());
      }
      map.put("series",series)
              .put("xAxis",xAxis);
      maps.put("map",map);
      maps.put("time",areaTime);
      return maps;
   }

   private List<DepartSalaryRecord> check( List<DepartSalaryRecord> records, AreaTimeVO areaTime) {
      System.out.println("areaTime = " + areaTime);
      System.out.println(records.size()+"=000000");
      List<String> ids = records.stream().map(DepartSalaryRecord::getDepartId).distinct().collect(Collectors.toList());
      System.out.println(ids.size()+"==ids");
      if (ids.size()<records.size()){
         records.removeAll(records);
         for(String id:ids){
            System.out.println("ids = " + id);
            DepartSalaryRecordExample e = new DepartSalaryRecordExample();
            e.createCriteria()
                    .andSalarySendTimeBetween(areaTime.getStart(),areaTime.getEnd())
                    .andDepartIdEqualTo(id);
            List<DepartSalaryRecord> thisIdRecords = departSalaryRecordMapper.selectByExample(e);
            System.out.println("thisIdRecords = " + thisIdRecords);
            if(thisIdRecords.size()==1){
               records.add(thisIdRecords.get(0));
               continue;
            }
            System.out.println(thisIdRecords.size());
            DepartSalaryRecord record = thisIdRecords.get(0);
            Double endowmentInsurance = thisIdRecords.stream().map(DepartSalaryRecord::getEndowmentInsurance).reduce(Double::sum).get();;
            Double unemployment = thisIdRecords.stream().map(DepartSalaryRecord::getUnemployment).reduce(Double::sum).get();
            Double medicare = thisIdRecords.stream().map(DepartSalaryRecord::getMedicare).reduce(Double::sum).get();
            Double rearInsurance = thisIdRecords.stream().map(DepartSalaryRecord::getRearInsurance).reduce(Double::sum).get();
            Double injuryInsurance = thisIdRecords.stream().map(DepartSalaryRecord::getInjuryInsurance).reduce(Double::sum).get();
            Double reservedFund = thisIdRecords.stream().map(DepartSalaryRecord::getReservedFund).reduce(Double::sum).get();
            Double salaryBasicCount = thisIdRecords.stream().map(DepartSalaryRecord::getSalaryBasicCount).reduce(Double::sum).get();
            Double salaryCount = thisIdRecords.stream().map(DepartSalaryRecord::getSalaryCount).reduce(Double::sum).get();
            DepartSalaryRecord record1 = new DepartSalaryRecord(record.getId(), record.getDepartId(), thisIdRecords.get(0).getSalarySendTime(),
                    salaryCount,  salaryBasicCount, endowmentInsurance, medicare,
                     unemployment, injuryInsurance, rearInsurance,  reservedFund);
            records.add(record1);
         }

      }
      return records;
   }

   @Override
   public List aspect(AreaTimeVO areaTime,String departName) {
      List pieData=new ArrayList();

      areaTime.setStart(areaTime.getStart()==null?TimeUtil.getLastMonthBegin():areaTime.getStart());
      areaTime.setEnd(areaTime.getEnd()==null?TimeUtil.getLastMonthEnd():areaTime.getEnd());
      //获取所在的规定月份的工资
      Department department=departmentRepository.getByNames(departName);
      DepartSalaryRecordExample e = new DepartSalaryRecordExample();
      e.createCriteria()
              .andSalarySendTimeBetween(areaTime.getStart(),areaTime.getEnd())
      .andDepartIdEqualTo(department.getDno());
      List<DepartSalaryRecord> recordList = departSalaryRecordMapper.selectByExample(e);
      List<DepartSalaryRecord> check = check(recordList, areaTime);
      DepartSalaryRecord cord=check.get(0);
      Pie p1 = new Pie();
      p1.setName("生育保险金");
      p1.setValue(cord.getRearInsurance().toString());
      pieData.add(p1);

      Pie p2 = new Pie();
      p2.setName("养老保险金");
      p2.setValue(cord.getEndowmentInsurance().toString());
      pieData.add(p2);

      Pie p3 = new Pie();
      p3.setName("工商保险金");
      p3.setValue(cord.getInjuryInsurance().toString());
      pieData.add(p3);

      Pie p4 = new Pie();
      p4.setName("医疗保险金");
      p4.setValue(cord.getMedicare().toString());
      pieData.add(p4);

      Pie p5 = new Pie();
      p5.setName("公积金");
      p5.setValue(cord.getReservedFund().toString());
      pieData.add(p5);

      Pie p6 = new Pie();
      p6.setName("基础工资");
      p6.setValue(cord.getSalaryBasicCount().toString());
      pieData.add(p6);

      Pie p7 = new Pie();
      p7.setName("总下发数目");
      p7.setValue(cord.getSalaryCount().toString());

      Pie p8 = new Pie();
      pieData.add(p8);
      p8.setName("失业保险金");
      p8.setValue(cord.getUnemployment().toString());

      return pieData;
   }

   @Override
   public Map<String,Object> record(AreaTimeVO areaTimeVO) {
      List<DepartSalaryRecord> data = departSalaryRecordMapper.selectByExample(new DepartSalaryRecordExample());
      List response=new ArrayList(20);
      for (DepartSalaryRecord record:data){
         Department department = departmentRepository.getDepartmentByDno(record.getDepartId());
         DecorateMap put = new DecorateMap()
                 .put("id",record.getId())
                 .put("dname", department.getDName())
                 .put("dno", department.getDno())
                 .put("medicare", record.getMedicare())
                 .put("injury", record.getInjuryInsurance())
                 .put("endowment", record.getEndowmentInsurance())
                 .put("rear", record.getRearInsurance())
                 .put("unemployment", record.getUnemployment())
                 .put("reservedFund", record.getReservedFund())
                 .put("basicCount", record.getSalaryBasicCount())
                 .put("count", record.getSalaryCount())
                 .put("sendTime", TimeUtil.getYMDHMS(record.getSalarySendTime()));
         response.add(put);
      }
      return new DecorateMap()
              .put("data",response)
               .put("msg","")
               .put("code",0)
               .put("total",response.size());
   }

   //开始计算上月份部门工资
   private void startCaculate(){
      PageHelper<Department> dhelper = new PageHelper<>();
      dhelper.setParam(new Department());
      //获取所有的部门
      List<Department> departments = departmentRepository.getDepartments(dhelper);

      for (Department d:departments){
         PageHelper<User> uhelper = new PageHelper<>();
         User user = new User();
         user.setDepartmentNo(d.getDno());
         uhelper.setParam(user);
         //根据部门的编号获取到员工的编号
         List<User> users = userRepository.getUsers(uhelper);
         Double departSalary=0d;
         AreaTimeVO areaTime=new AreaTimeVO();
         areaTime.setStart(TimeUtil.getLastMonthBegin());
         areaTime.setEnd(TimeUtil.getLastMonthEnd());
         for(User u:users){
            //根据员工的编号获取到所发的薪水数目
            Salary byAreaTime = salaryRepository.getByAreaTime(u.getUserNo(),areaTime)
                    .size()>0?salaryRepository.getByAreaTime(u.getUserNo(),areaTime).get(0):null;
            if(byAreaTime==null){//没有查出此员工的薪资信息
               continue;
            }
            BigDecimal count=count(byAreaTime);
            departSalary+=count.doubleValue();
         }
         //五险一金
         insuranceAndFund(departSalary,d);


      }
   }

   private void insuranceAndFund(
                                 Double departSalary,Department department) {
      //公司所缴纳的五险一金情况
      SalaryRate salaryRate = salaryRateMapper.selectByPrimaryKey(1);
      DepartSalaryRecord record = new DepartSalaryRecord();
      record.setDepartId(department.getDno());
      record.setEndowmentInsurance((salaryRate.getEndowmentInsuranceRate()*departSalary));
      record.setInjuryInsurance(salaryRate.getInjuryInsuranceRate()*departSalary);
      record.setMedicare(salaryRate.getMdeicareRate()*departSalary);
      record.setRearInsurance(salaryRate.getRearInsuranceRate()*departSalary);
      record.setReservedFund(salaryRate.getReservedFundRate()*departSalary);
      record.setUnemployment(salaryRate.getUnemploymentRate()*departSalary);
      record.setSalarySendTime(new Date());
      record.setSalaryBasicCount(departSalary);
      record.setSalaryCount(count(record));
      departSalaryRecordMapper.insertSelective(record);
   }
      //五险一金+基础薪资之和
   private Double count(DepartSalaryRecord record) {
      return record.getEndowmentInsurance()+record.getInjuryInsurance()
              +record.getMedicare()+record.getRearInsurance()
              +record.getReservedFund()+record.getUnemployment()
              +record.getSalaryBasicCount();
   }

   private BigDecimal count(Salary salary) {
      //底薪+绩效-缺勤
      BigDecimal end = salary.getBasic().add(salary.getPerformance()).subtract(salary.getWage());
      return end;
   }
}


