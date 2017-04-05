package com.qbao.im.api.dao;

import java.util.List;

import com.qbao.im.api.entity.Users;

//@Repository
public interface IUserDao {

	List<Users> getAllUser();
}
