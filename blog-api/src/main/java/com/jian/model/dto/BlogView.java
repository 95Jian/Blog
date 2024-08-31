package com.jian.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 博客浏览量
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogView {
	private Long id;
	private Integer views;
}
