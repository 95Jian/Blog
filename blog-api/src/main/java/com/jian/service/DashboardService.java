package com.jian.service;


import java.util.List;
import java.util.Map;

public interface DashboardService {

	int getBlogCount();


	Map<String, List> getCategoryBlogCountMap();

	Map<String, List> getTagBlogCountMap();

}
