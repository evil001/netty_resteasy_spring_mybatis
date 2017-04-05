package com.qbao.im.api.entity;

import com.qbao.im.api.DBEnum;

import java.io.Serializable;

/**
 * Created by tangxiaojun on 2017/3/28.
 */
public class Friend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String friendId;

    private int sourceType;

    private String remarkName;

    public static void main(String[] args) {
        Friend friend = new Friend();
        friend.setUserId(1002l+"");
        System.out.printf("tabName:+"+friend.getTabName());
    }

    @Override
    public String getTabName() {
        int modVal = (int) (Integer.parseInt(this.userId)%32);
        return DBEnum.FRIEND_SHIP.getValue().concat(modVal+"");
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
        int modVal = (int) (Integer.parseInt(this.userId)%32);
        String tabName = DBEnum.FRIEND_SHIP.getValue().concat(modVal+"");
        super.setTabName(tabName);
    }

    public String getFriendId() {
        return friendId;
    }

    public void setFriendId(String friendId) {
        this.friendId = friendId;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

}
