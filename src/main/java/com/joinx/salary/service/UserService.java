package com.joinx.salary.service;

import com.joinx.salary.pojo.User;
import com.joinx.salary.util.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author 杨鋆诺
 * @Date 2019/11/16
 * @Description 用户业务层
 */
@Transactional
public interface UserService {
      User loginUser(User user);
   
   List<User> userInfoByCondition(PageHelper<User> pageHelper);
   
   void saveUser(User user, String roleId);

    int update(User user);

    Map<String,Object> collectAllInfo();

    void deleteUser(String userNo);

    User userInfoByUno(String eno);

    User getUserByLoginName(String loginName);

    void removeByUserNos();

    User getByUserNo(User user);
}
