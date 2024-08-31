package com.jian.mapper;

import com.jian.entity.About;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 关于我持久层接口
 */
@Mapper
@Repository
public interface AboutMapper {
	List<About> getList();

	int updateAbout(String nameEn, String value);

	String getAboutCommentEnabled();
}
