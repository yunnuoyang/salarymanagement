package com.joinx.salary.repository;

import com.joinx.salary.pojo.Leave;
import com.joinx.salary.util.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface LeaveRepository {
   List<Leave> isExitThatDay(@Param("userNo") String userNo, @Param("areaStart") Date areaStart,
                       @Param("areaEnd")Date areaEnd);
   
   List<Leave> leaves( PageHelper<Leave> pageHelper);

   void update(Leave l);

   void save(Leave l);

   List<Leave> list(@Param("userNo") String userNo);

   void updateByLno(Leave leave);
}
