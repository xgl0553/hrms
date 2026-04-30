package com.qingjian.hrms.service;

import com.qingjian.hrms.entity.User;
import com.qingjian.hrms.qo.UserQO;

import java.util.List;

public interface UserService {
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int addUser(User user);

    User findById(Integer id);

    void deleteById(Integer id);

    void edit(User user);

    void add(User user);

    List<User> list(UserQO userQO);

    void editSelective(User user);
}
