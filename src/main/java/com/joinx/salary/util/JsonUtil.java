package com.joinx.salary.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.joinx.salary.pojo.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
public class JsonUtil {
   private static ObjectMapper objectMapper = new ObjectMapper();
   public static String objToJson(Object obj) throws JsonProcessingException {
      String string = objectMapper.writeValueAsString(obj);
      return string;
   }
   public static <T>T jsonToObj(Class cls,String json) throws IOException {
      ObjectReader reader = objectMapper.readerFor(cls);
      Object o = reader.readValue(json);
      return (T)o;
   }
}
