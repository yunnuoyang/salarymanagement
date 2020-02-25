package com.joinx.salary.util;

import com.joinx.salary.pojo.Salary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author
 * @Date
 * @Description
 */
//生成各个表单的表单号码
public class NumberGenerator{
   public enum TableName {
     salary, attendance, leave, department
   }
   private  String flag="";
   public NumberGenerator() {
      SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd");
      String format = smf.format(new Date(System.currentTimeMillis()));
      String[] split = format.split("\\-");
      for(String str:split){
         flag+=str;
      }
   }
   public  String generator(){
      String uuid = UUID.randomUUID().toString().replaceAll("-","");
      return flag+uuid.substring(0,uuid.length()-8);
   }
   
   public static void main(String[] args) {
      String generator = new NumberGenerator().generator();
      System.out.println(generator);
   }
}
