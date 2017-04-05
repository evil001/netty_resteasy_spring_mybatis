package com.qbao.im.api.common.msg;

/**
 * Created by tangxiaojun on 2017/3/23.
 */
public class ClientMessage extends BaseMessage{
    // 错误描述信息
    private String errorMsg;

    // 错误码
    private Integer errorCode = 0;

    // 对象信息
    private Object data;

    /**
     * 1000 : 访问正常 10001：当前接口弃用需要客户端强制升级 1002：维护中 (提示消息放入errorMsg字段中)
     * 1003：当前访问的接口有新版本可使用 1004： jsession失效 1005：接口异常或错误
     */
    private Integer responseCode = ErrorCode.RET_SUCCESS;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public ClientMessage(String errorMsg, Integer errorCode, Object data, Integer responseCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.data = data;
        this.responseCode = responseCode;
    }

    public ClientMessage(Object data,String errorMsg){
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public ClientMessage(String cmd, String userid, String cometid, Integer sessionid, Integer platform, Object data, String errorMsg){
        super.setCmd(cmd);
        super.setUserid(userid);
        super.setCometid(cometid);
        super.setSessionid(sessionid);
        super.setPlatform(platform);
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public ClientMessage(String cmd , String  userid,String cometid,Integer sessionid,Integer platform,
                         String errorMsg, Integer errorCode, Object data, Integer responseCode){
        super.setCmd(cmd);
        super.setUserid(userid);
        super.setCometid(cometid);
        super.setSessionid(sessionid);
        super.setPlatform(platform);
        this.data = data;
        this.errorMsg = errorMsg;
        this.responseCode = responseCode;
        this.errorCode = errorCode;
    }


    public static final ClientMessage success(String cmd , String userid,String cometid,Integer sessionid,Integer platform,Object data,String errorMsg){
        return new ClientMessage(cmd,userid,cometid,sessionid,platform,data,errorMsg);
    }

    public static final ClientMessage success(String cmd , String  userid,String cometid,Integer sessionid,Integer platform,Object data){
        return new ClientMessage(cmd,userid,cometid,sessionid,platform,data,null);
    }

    public static final ClientMessage success() {
        return new ClientMessage(null, null);
    }

    public static final ClientMessage success(Object data, Integer errorCode) {
        return new ClientMessage(null, errorCode, data, ErrorCode.RET_SUCCESS);
    }

//    public static final ClientMessage success(String cmd,Long userId, Long cometid, String serverid, String sessionId, String platform, Object data) {
//        return new ClientMessage(cmd,userId,cometid,serverid,sessionId,platform,null,null,data, ErrorCode.RET_SUCCESS);
//    }

    public static final ClientMessage success(Object data, String msg) {
        return new ClientMessage(data, msg);
    }

    public static final ClientMessage failed() {
        return new ClientMessage(null, null, null, 1005);
    }

    public static final ClientMessage failed(String errorMsg) {
        return new ClientMessage(null, errorMsg);
    }

    public static final ClientMessage failed(String errorMsg, Integer responseCode) {
        return new ClientMessage(errorMsg, null, null, responseCode);
    }

    public static final ClientMessage failed(Integer responseCode,String errorMsg){
        return new ClientMessage(errorMsg,null,null,responseCode);
    }

    public static final ClientMessage failed(Object data, String errorMsg) {
        return new ClientMessage(errorMsg, null, data, ErrorCode.RET_SYS_BASE);
    }

    public static final ClientMessage failed(String errorMsg, Integer responseCode, Integer errorCode) {
        return new ClientMessage(errorMsg, errorCode, null, responseCode);
    }

    public static final ClientMessage failed(String cmd , String userid,String  cometid,Integer sessionid,Integer platform){
        return new ClientMessage(cmd,userid,cometid,sessionid,platform,null,null,null,ErrorCode.RET_SYS_BASE);
    }

    public static final ClientMessage failed(String cmd , String  userid,String  cometid,Integer sessionid,Integer platform,Integer responseCode,String errorMsg){
        return new ClientMessage(cmd,userid,cometid,sessionid,platform,errorMsg,null,null,ErrorCode.RET_SYS_BASE);
    }
}
