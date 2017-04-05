package com.qbao.im.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.qbao.im.api.common.msg.ClientMessage;
import com.qbao.im.api.common.msg.ErrorCode;
import com.qbao.im.api.common.msg.ErrorMsg;
import com.qbao.im.api.constans.OperConstants;
import com.qbao.im.api.entity.Friend;
import com.qbao.im.api.service.friend.IFriendService;
import com.qianbao.framework.datasource.annotation.DataSource;

/**
 * Created by tangxiaojun on 2017/3/28.
 */
@Service
public class FriendServiceImpl implements IFriendService {

	private static final Logger logger = LoggerFactory.getLogger(FriendServiceImpl.class);

	// @Autowired
	// private IFriendDao friendDao;

	@Override
	public int save(@DataSource(field = "userId") Long userId, Friend friend) throws Exception {
		// return this.friendDao.save(friend);
		return 0;
	}

	@Override
	public List<Friend> getFriends(@DataSource(field = "userId") Long userId, Friend friend) {
		// return this.friendDao.getFriends(friend);
		return null;
	}

	@Override
	public List<Friend> getFriends(@DataSource(field = "userId") Long userId) {
		Friend friend = new Friend();
		friend.setUserId(userId.toString());
		// return this.friendDao.getFriends(friend);
		return null;
	}

	@Override
	public ClientMessage oper(@DataSource(field = "userId") Long userId, String oper, Friend friend) {
		if (oper.equals(OperConstants.ADD)) {
			if (this.getFriends(Long.valueOf(friend.getUserId()), friend).size() > 0) {
				return ClientMessage.failed(ErrorCode.RET_FRIEND_ALREADY_EXISTED,
						ErrorMsg.getErrorDetail(ErrorCode.RET_FRIEND_ALREADY_EXISTED));
			}
			try {
				int result = this.save(Long.valueOf(friend.getUserId()), friend);
				if (result > 0) {
					return ClientMessage.success();
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("call save friend error,error:{}", e);
				return ClientMessage.failed();
			}
			return ClientMessage.failed();
		}

		if (oper.equals(OperConstants.UPDATE)) {
			return ClientMessage.failed();
		}

		if (oper.equals(OperConstants.DEL)) {
			return ClientMessage.failed();
		}
		return ClientMessage.failed();
	}
}
