package com.joinx.salary.pojo;

import lombok.*;
/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 用户信息
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
   private Integer id;//主键id
   private String userNo;//员工编号
   private String departmentNo;//部门编号
   private String loginName;//登陆名称
   private String sex;//登陆名称
   private String password;//密码
   private String realName;//真实名称
   private String address;//家庭住址
   private String tel;//联系电话
   private String email;//工作邮箱
   private String status;//在职状态
   private Integer workAge;//工龄
   private Role role;
   
   
}
