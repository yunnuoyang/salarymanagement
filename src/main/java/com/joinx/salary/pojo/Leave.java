package com.joinx.salary.pojo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 请假信息
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Leave {
   private Long id;//主键
   private String lno;//请假编号
   private String reason;//请假原因
   private String eno;//请假的员工编号
   @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
   private Date startTime;//开始请假时间
   @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
   private Date endTime;//终止请假时间
   private Date leaveApply;//请假发起时间
   /**
    * 处理请假的时间
    */
   private Date operateTime;
   /**
    * 0:待处理1：不允许请假2：允许请假
    */

   private Integer operateResult;
}
