package com.jian.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description: 登录账号密码
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginInfo {
	private String username;
	private String password;
}
