package com.jian.mapper;

import com.jian.entity.Moment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 博客动态持久层接口
 */
@Mapper
@Repository
public interface MomentMapper {
	List<Moment> getMomentList();

	int addLikeByMomentId(Long momentId);

	int updateMomentPublishedById(Long momentId, Boolean published);

	Moment getMomentById(Long id);

	int deleteMomentById(Long id);

	int saveMoment(Moment moment);

	int updateMoment(Moment moment);
}
