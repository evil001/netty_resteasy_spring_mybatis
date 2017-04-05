package com.qbao.im.api.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//import org.jboss.resteasy.annotations.Form;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.qbao.im.api.common.msg.ClientMessage;

@Controller
@Path("/api")
public class FriendController {

	private static final Logger logger = LoggerFactory.getLogger(FriendController.class);

	// @Autowired
	// private UserBizImpl userBizImpl;

	// @Autowired
	// private FriendServiceImpl friendServiceImpl;

	@GET
	@Path("/test")
	@Produces(MediaType.APPLICATION_JSON)
	public ClientMessage test() {
		logger.info("================================");
		// List<Users> user = this.userBizImpl.getAllUsers();
		System.out.println("11111111111111");
		// System.out.println(JSONObject.toJSONString(user));
		return ClientMessage.success();
	}

	// @GET
	// @Path("/operFriend/{oper}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public ClientMessage operFriend(@PathParam("oper") String oper,
	// @QueryParam("userId") Long userId,
	// @QueryParam("friendId") Long friendId, @QueryParam("sourceType") Integer
	// sourceType,
	// @QueryParam("remarkName") String remarkName) {
	// Friend friend = new Friend();
	// friend.setUserId(userId+"");
	// friend.setFriendId(friendId+"");
	// friend.setSourceType(sourceType);
	// friend.setRemarkName(remarkName);
	// return this.friendServiceImpl.oper(userId, oper, friend);
	// }

	/**
	 * 获取好友列表
	 *
	 * @param userId
	 * @return
	 */
	// @GET
	// @Path("/getFriends/{userid}")
	// @Produces(MediaType.APPLICATION_JSON)
	// public ClientMessage getFriends(@PathParam("userid") Long userId,
	// @QueryParam("cmd") String cmd,
	// @QueryParam("cometid") String cometid, @QueryParam("platform") Integer
	// platform,
	// @QueryParam("sessionid") Integer sessionId) {
	// try {
	// return ClientMessage.success(cmd, userId.toString(), cometid, sessionId,
	// platform,
	// this.friendServiceImpl.getFriends(userId));
	// } catch (Exception e) {
	// e.printStackTrace();
	// logger.error("call getFriends controller error:{}", e);
	// return ClientMessage.failed();
	// }
	// }
}
