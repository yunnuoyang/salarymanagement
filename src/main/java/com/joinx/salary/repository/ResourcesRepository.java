package com.joinx.salary.repository;

import com.joinx.salary.pojo.ReSources;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResourcesRepository {
   /**
    * 通过角色的编号获取资源的信息
    * @param roleNo
    * @return
    */
   List<ReSources> getResourcesByRoleNo(@Param("role_no")String roleNo);
}
