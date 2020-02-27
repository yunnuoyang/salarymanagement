package com.joinx.salary.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 工资信息
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Salary {
   
   private Integer id;//主键
   private String sno;//工资编号
   private String eno;//员工编号
   private BigDecimal basic;//基本工资
   private BigDecimal performance;//绩效奖金
   private BigDecimal wage;//奖惩金
   private Date permitTime;//工资下发时间

   private Double insuranceCount;//五险一金的总和

   private Double finalSalary;//最终下发的工资
}
