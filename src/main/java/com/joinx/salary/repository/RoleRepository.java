package com.joinx.salary.repository;

import com.joinx.salary.pojo.Role;
import com.joinx.salary.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface RoleRepository {
   Role getRoleByUserNo(@Param("user_no") String userNo);
   
   void saveToURTable(@Param("userNo") String userNo, @Param("roleNo") String roleId);
   
   List<Role> getRoles();

    void delete(String userNo);

    void removeUserRoleByUserNos(@Param("unEmp") List<String> unEmp);
}
