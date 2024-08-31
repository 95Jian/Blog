package com.jian.model.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 自定义爱好
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Favorite {
	private String title;
	private String content;
}
