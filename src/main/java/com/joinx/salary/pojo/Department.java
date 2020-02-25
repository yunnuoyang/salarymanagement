package com.joinx.salary.pojo;

import lombok.*;

/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 部门类
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
   private Long id;//主键id
   private String dno;//部门编号
   private String dName;//部门名称
}
