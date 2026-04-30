package com.qingjian.hrms.mapper;

import com.qingjian.hrms.entity.User;
import com.qingjian.hrms.qo.UserQO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户表 UserMapper
 * @author qingjian
 * @date 2021-04-25 14:25:42
 */
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User findByUsername(String username);

    List<User> list(UserQO userQO);
}
