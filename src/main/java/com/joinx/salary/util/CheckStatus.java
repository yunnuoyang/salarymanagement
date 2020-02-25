package com.joinx.salary.util;

import lombok.Data;

/**
 * @Author
 * @Date
 * @Description
 */
public enum CheckStatus {
   Dimission("离职","0"),
   Entrant("在职","1")
   ;
   String description;
   String status;
   CheckStatus(String description, String status) {
      this.description = description;
      this.status = status;
   }
   
   public String getStatus() {
      return status;
   }
}
