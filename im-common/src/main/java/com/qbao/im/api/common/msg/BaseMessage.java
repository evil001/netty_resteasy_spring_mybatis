package com.qbao.im.api.common.msg;

/**
 * Created by tangxiaojun on 2017/3/29.
 */
public class BaseMessage {

    private String cmd;

    private String userid;

    private String cometid;

    private Integer sessionid;

    private Integer platform;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCometid() {
        return cometid;
    }

    public void setCometid(String cometid) {
        this.cometid = cometid;
    }

    public Integer getSessionid() {
        return sessionid;
    }

    public void setSessionid(Integer sessionid) {
        this.sessionid = sessionid;
    }

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }
}
