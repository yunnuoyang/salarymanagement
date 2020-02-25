package com.joinx.salary.util;

/**
 * @Author
 * @Date
 * @Description
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

/**
 * 采用MD5进行非对称加密
 */
@Component
public class PasswordEncoder {
   @Value("${util.md5.salt}")
   private String salt;
   @Value("${util.md5.passowrd}")
   private String password;

   public String getDefaultPassword() {
      return password;
   }

   /**
    * 传入用户密码
    * @param password
    * @return
    */
   public  String encode(String password){
      String encode = DigestUtils.md5DigestAsHex((salt+password).getBytes());
      return encode;
   }
   
   public static void main(String[] args) {
      String encode = new PasswordEncoder().encode("salary12345");
      System.out.println(encode);
   }
}
