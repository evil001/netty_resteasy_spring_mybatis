package com.qbao.im.api.service.friend;

import com.qbao.im.api.common.msg.ClientMessage;
import com.qbao.im.api.entity.Friend;

import java.util.List;

/**
 * Created by tangxiaojun on 2017/3/28.
 */
public interface IFriendService {

    int save(Long userId, Friend friend) throws Exception;

    /**
     * 获取指定好友列表
     * @param friend
     * @return
     */
    List<Friend> getFriends(Long userId,Friend friend);

    List<Friend> getFriends(Long userId);

    /**
     * 增删改
     * @param oper
     * @param friend
     * @return
     */
    ClientMessage oper(Long userId,String oper, Friend friend);
}
