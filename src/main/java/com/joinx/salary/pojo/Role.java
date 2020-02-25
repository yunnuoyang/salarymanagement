package com.joinx.salary.pojo;

import lombok.*;
/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 角色信息
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
   private Long id;//主键id
   private String rno;//角色编号
   private String name;//角色名称
}
