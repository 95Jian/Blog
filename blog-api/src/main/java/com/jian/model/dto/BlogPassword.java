package com.jian.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 受保护文章密码DTO
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BlogPassword {
	private Long blogId;
	private String password;
}
