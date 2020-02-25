package com.joinx.salary.pojo;

import lombok.*;

/**
 * @Author
 * @Date
 * @Description
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
   private Integer tag;
   private String info;
   private Object obj;

   public ResponseDto(Integer tag, String info) {
      this.tag = tag;
      this.info = info;
   }
}
