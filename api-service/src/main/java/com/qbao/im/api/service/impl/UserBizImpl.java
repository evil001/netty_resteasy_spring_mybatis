package com.qbao.im.api.service.impl;

import java.util.List;

import com.qbao.im.api.entity.Users;
import com.qbao.im.api.service.user.IUserBiz;

//@Service
public class UserBizImpl implements IUserBiz {

	// @Autowired
	// private IUserDao userDao;

	// @Autowired
	// private RedisTemplate redisTemplate;

	@Override
	public List<Users> getAllUsers() {
		// List<Users> userList = this.userDao.getAllUser();
		// redisTemplate.execute(new RedisCallback() {
		// @Override
		// public Object doInRedis(RedisConnection connection) throws
		// DataAccessException {
		// connection.set(redisTemplate.getStringSerializer().serialize("user.id"+1),redisTemplate.getStringSerializer().serialize("测试redis插入数据"));
		// return null;
		// }
		// });
		// System.out.println(JSONObject.toJSONString(userList));
		// return this.userDao.getAllUser();
		// List<Users> userList = new ArrayList<Users>();
		// Users u = new Users();
		// u.setId(1l);
		// u.setUserName("aaaa");
		// u.setAge(111);
		// userList.add(u);
		// return userList;
		return null;
	}

}
