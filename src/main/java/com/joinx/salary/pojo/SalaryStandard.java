package com.joinx.salary.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * (SalaryStandard)实体类
 *
 * @author makejava
 * @since 2020-02-16 14:06:15
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalaryStandard implements Serializable {
    private static final long serialVersionUID = -71343159554216817L;
    
    private Integer id;
    
    private Double salaryBasic;
    
    private String userNo;



}