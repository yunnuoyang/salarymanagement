package com.joinx.salary.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 考勤类
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Attendance {
   
   private Long id;//主键id
   private String no;//考勤编号
   private String eno;//员工编号
   @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
   private Date attendanceEnd;//datetime考勤终止时间
   @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
   private Date attendanceStart;//考勤起始时间
}
