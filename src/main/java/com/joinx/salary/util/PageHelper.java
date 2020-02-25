package com.joinx.salary.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author
 * @Date
 * @Description
 */
@Data
@Setter
@Getter
public class PageHelper<T> {
   
   private T param;//分页查询的参数条件
   
   private int pageSize; // 每页显示多少条记录
   
   private int startIndex;//起始索引
   
   private int currentPage; //当前第几页数据
   
   
   private int totalPage; // 一共多少页
   
   private List<T> dataList; //查询结果数据
   
   public PageHelper() {
   }
   
   public PageHelper(T param, int pageSize, int currentPage) {
      this.param = param;
      this.pageSize = pageSize;
      this.currentPage=currentPage;
   }
   
   public int getCurrentPage() {
      return totalPage;
   }
   
   public int getStartIndex() {
      return (currentPage-1)*pageSize;
   }
   
   public int getTotalPage() {
      if(this.pageSize==0){
         this.pageSize=1;
      }
      return this.totalPage%pageSize!=0?totalPage/pageSize+1:totalPage/pageSize;
   }
   
   public List<T> getDataList() {
      List<T> ts = this.dataList.subList(this.getStartIndex(),
             this.pageSize * this.currentPage>dataList.size()?
                    dataList.size():this.pageSize * this.currentPage);
   ts.forEach(s->{
      System.out.println(s+"=====");
   });
      return ts;
   }
}
