package com.jian.mapper;

import com.jian.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Description: 用户持久层接口
 */
@Mapper
@Repository
public interface UserMapper {
	User findByUsername(String username);
}
