package com.jian.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class SiteSetting {
	private Long id;
	private String nameEn;
	private String nameZh;
	private String value;
	private Integer type;
}
