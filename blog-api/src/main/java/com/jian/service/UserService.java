package com.jian.service;

import com.jian.entity.User;

public interface UserService {
	User findUserByUsernameAndPassword(String username, String password);
}
