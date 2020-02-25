package com.joinx.salary.pojo;

import com.joinx.salary.util.TimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SalaryDay {
    @Value("${caculate.day}")
    private String caculateDay;

    public Date getCaculateDay() {
        String concat = TimeUtil.getYMD(new Date()).substring(0, 8).concat(caculateDay);
        System.out.println("concat = " + concat);
        SimpleDateFormat smf=new SimpleDateFormat("yyyy-MM-dd");
        try {
          return   smf.parse(concat);
        } catch (ParseException e) {

        }
        return null;
    }

    public static void main(String[] args) {
        new SalaryDay().getCaculateDay();
    }
}
