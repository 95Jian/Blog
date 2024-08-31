package com.jian.mapper;

import com.jian.entity.Blog;
import com.jian.model.dto.BlogView;
import com.jian.model.dto.BlogVisibility;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.jian.model.vo.ArchiveBlog;
import com.jian.model.vo.BlogDetail;
import com.jian.model.vo.BlogInfo;
import com.jian.model.vo.CategoryBlogCount;
import com.jian.model.vo.NewBlog;
import com.jian.model.vo.RandomBlog;
import com.jian.model.vo.SearchBlog;

import java.util.List;

/**
 * @Description: 博客文章持久层接口
 */
@Mapper
@Repository
public interface BlogMapper {
	List<Blog> getListByTitleAndCategoryId(String title, Integer categoryId);

	List<SearchBlog> getSearchBlogListByQueryAndIsPublished(String query);

	List<Blog> getIdAndTitleList();

	List<NewBlog> getNewBlogListByIsPublished();

	List<BlogInfo> getBlogInfoListByIsPublished();

	List<BlogInfo> getBlogInfoListByCategoryNameAndIsPublished(String categoryName);

	List<BlogInfo> getBlogInfoListByTagNameAndIsPublished(String tagName);

	List<String> getGroupYearMonthByIsPublished();

	List<ArchiveBlog> getArchiveBlogListByYearMonthAndIsPublished(String yearMonth);

	List<RandomBlog> getRandomBlogListByLimitNumAndIsPublishedAndIsRecommend(Integer limitNum);

	List<BlogView> getBlogViewsList();

	int deleteBlogById(Long id);

	int deleteBlogTagByBlogId(Long blogId);

	int saveBlog(com.jian.model.dto.Blog blog);

	int saveBlogTag(Long blogId, Long tagId);

	int updateBlogRecommendById(Long blogId, Boolean recommend);

	int updateBlogVisibilityById(Long blogId, BlogVisibility bv);

	int updateBlogTopById(Long blogId, Boolean top);

	int updateViews(Long blogId, Integer views);

	Blog getBlogById(Long id);

	String getTitleByBlogId(Long id);

	BlogDetail getBlogByIdAndIsPublished(Long id);

	String getBlogPassword(Long blogId);

	int updateBlog(com.jian.model.dto.Blog blog);

	int countBlog();

	int countBlogByIsPublished();

	int countBlogByCategoryId(Long categoryId);

	int countBlogByTagId(Long tagId);

	Boolean getCommentEnabledByBlogId(Long blogId);

	Boolean getPublishedByBlogId(Long blogId);

	List<CategoryBlogCount> getCategoryBlogCountList();
}
