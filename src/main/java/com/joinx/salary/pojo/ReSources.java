package com.joinx.salary.pojo;

import lombok.*;
/**
 *
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 资源信息
 */
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReSources {
   private Integer id;//主键id
   private String resourcesNo;//资源编号
   private String url;//url资源
   private String button;//button资源
   
   
   
}
