package com.qbao.im.api.dao;

import java.util.List;

import com.qbao.im.api.entity.Friend;

/**
 * Created by tangxiaojun on 2017/3/28.
 */
// @Repository
public interface IFriendDao {

	/**
	 * 添加好友关系
	 * 
	 * @param friend
	 * @return
	 */
	int save(Friend friend);

	/**
	 * 修改好友关系
	 * 
	 * @param friend
	 * @return
	 */
	int modify(Friend friend);

	/**
	 * 删除好友关系(物理删除)
	 * 
	 * @param friend
	 * @return
	 */
	int del(Friend friend);

	/**
	 * 查询该用户所有好友信息
	 * 
	 * @param friend
	 * @return
	 */
	List<Friend> getFriends(Friend friend);
}
