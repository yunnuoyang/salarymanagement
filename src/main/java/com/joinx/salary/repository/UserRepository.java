package com.joinx.salary.repository;

import com.joinx.salary.pojo.User;
import com.joinx.salary.util.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserRepository {
        /**
         * 获取所有的用户信息
         * @return
         */
        List<User> getUsers(User user);
        
        /**
         * 进行用户登陆的校验
         * @param user
         * @return
         */
        User loginUser(User user);
        
        List<User> getUsers(PageHelper<User> pageHelper);
        int count();
        
        void save(User user);

    int update(User user);

    void delete(String userNo);

    User userInfoByUno(String eno);

    User getUserByLoginName(String loginName);

    void removeByUserNos(@Param("unEmp") List<String> unEmp);
}
