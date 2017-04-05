package com.qbao.im.api;

/**
 * Created by tangxiaojun on 2017/3/28.
 */
public enum DBEnum {

    FRIEND_SHIP("nim_friend_");

    private String value;

    private DBEnum(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static void main(String[] args) {
        System.out.println(DBEnum.FRIEND_SHIP.getValue());
    }
}
