package com.joinx.salary.pojo;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaTimeVO {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end;
}
