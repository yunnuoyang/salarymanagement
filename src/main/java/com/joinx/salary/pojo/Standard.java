package com.joinx.salary.pojo;

import lombok.*;

import java.math.BigDecimal;

/**
 * @Author
 * @Date
 * @Description 定义标准
 */
@Setter
@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Standard {
   private Integer id;
   private Double onlineDays;//月工作天数
   private Double onlineHours;//日工作时长
   private Double workStartHour;
}
