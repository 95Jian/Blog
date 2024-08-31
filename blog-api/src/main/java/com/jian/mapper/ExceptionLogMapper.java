package com.jian.mapper;

import com.jian.entity.ExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 异常日志持久层接口
 */
@Mapper
@Repository
public interface ExceptionLogMapper {
	List<ExceptionLog> getExceptionLogListByDate(String startDate, String endDate);

	int saveExceptionLog(ExceptionLog log);

	int deleteExceptionLogById(Long id);
}
