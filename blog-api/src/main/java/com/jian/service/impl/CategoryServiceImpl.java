package com.jian.service.impl;

import com.jian.config.RedisKeyConfig;
import com.jian.entity.Category;
import com.jian.exception.NotFoundException;
import com.jian.mapper.CategoryMapper;
import com.jian.service.CategoryService;
import com.jian.service.RedisService;
import com.jian.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jian.exception.PersistenceException;

import java.util.List;

/**
 * @Description: 博客分类业务层实现
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryMapper categoryMapper;
	@Autowired
	TagService tagService;
	@Autowired
	RedisService redisService;

	@Override
	public List<Category> getCategoryList() {
		return categoryMapper.getCategoryList();
	}

	@Override
	public List<Category> getCategoryNameList() {
		String redisKey = RedisKeyConfig.CATEGORY_NAME_LIST;
		List<Category> categoryListFromRedis = redisService.getListByValue(redisKey);
		if (categoryListFromRedis != null) {
			return categoryListFromRedis;
		}
		List<Category> categoryList = categoryMapper.getCategoryNameList();
		redisService.saveListToValue(redisKey, categoryList);
		return categoryList;
	}

	@Transactional
	@Override
	public void saveCategory(Category category) {
		if (categoryMapper.saveCategory(category) != 1) {
			throw new PersistenceException("分类添加失败");
		}
		redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_NAME_LIST);
	}

	@Override
	public Category getCategoryById(Long id) {
		Category category = categoryMapper.getCategoryById(id);
		if (category == null) {
			throw new NotFoundException("分类不存在");
		}
		return category;
	}

	@Override
	public Category getCategoryByName(String name) {
		return categoryMapper.getCategoryByName(name);
	}

	@Transactional
	@Override
	public void deleteCategoryById(Long id) {
		if (categoryMapper.deleteCategoryById(id) != 1) {
			throw new PersistenceException("删除分类失败");
		}
		redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_NAME_LIST);
	}

	@Transactional
	@Override
	public void updateCategory(Category category) {
		if (categoryMapper.updateCategory(category) != 1) {
			throw new PersistenceException("分类更新失败");
		}
		redisService.deleteCacheByKey(RedisKeyConfig.CATEGORY_NAME_LIST);
		//修改了分类名，可能有首页文章关联了分类，也要更新首页缓存
		redisService.deleteCacheByKey(RedisKeyConfig.HOME_BLOG_INFO_LIST);
	}
}
