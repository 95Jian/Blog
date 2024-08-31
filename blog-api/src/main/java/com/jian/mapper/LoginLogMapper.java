package com.jian.mapper;

import com.jian.entity.LoginLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 登录日志持久层接口
 */
@Mapper
@Repository
public interface LoginLogMapper {
	List<LoginLog> getLoginLogListByDate(String startDate, String endDate);

	int saveLoginLog(LoginLog log);

	int deleteLoginLogById(Long id);
}
