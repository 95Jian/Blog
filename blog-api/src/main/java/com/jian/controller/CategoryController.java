package com.jian.controller;

import com.jian.annotation.VisitLogger;
import com.jian.model.vo.BlogInfo;
import com.jian.model.vo.PageResult;
import com.jian.model.vo.Result;
import com.jian.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
	@Autowired
	BlogService blogService;

	/**
	 * 根据分类name分页查询公开博客列表
	 *
	 * @param categoryName 分类name
	 * @param pageNum      页码
	 * @return
	 */
	@VisitLogger(behavior = "查看分类")
	@GetMapping("/category")
	public Result category(@RequestParam String categoryName,
						   @RequestParam(defaultValue = "1") Integer pageNum) {
		PageResult<BlogInfo> pageResult = blogService.getBlogInfoListByCategoryNameAndIsPublished(categoryName, pageNum);
		return Result.ok("请求成功", pageResult);
	}
}
