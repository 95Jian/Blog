package com.jian.controller.admin;

import com.jian.model.vo.Result;
import com.jian.service.DashboardService;
import com.jian.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class DashboardAdminController {
	@Autowired
	DashboardService dashboardService;
	@Autowired
	RedisService redisService;

	@GetMapping("/dashboard")
	public Result dashboard() {
		int blogCount = dashboardService.getBlogCount();
		Map<String, List> categoryBlogCountMap = dashboardService.getCategoryBlogCountMap();
		Map<String, List> tagBlogCountMap = dashboardService.getTagBlogCountMap();

		Map<String, Object> map = new HashMap<>();
		map.put("blogCount", blogCount);
		map.put("category", categoryBlogCountMap);
		map.put("tag", tagBlogCountMap);
		return Result.ok("获取成功", map);
	}
}
