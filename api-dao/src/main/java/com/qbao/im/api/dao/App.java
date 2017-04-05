package com.qbao.im.api.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class App {

	public static final Map<String, Object> map = new HashMap<>();

	static {
		map.put("FDLISTRQ", "FDLISTRS");
	}

	public static void main(String[] args) {
		// byte[] b = new byte[1024];
		// b[0] = 00;
		// b[1] = 02;
		// b[2] = 22;
		// b[3] = 124;
		// b[4] = 38;
		// byte[] a = new byte[b.length - 2];
		// System.arraycopy(b, 2, a, 0, a.length);
		// for (byte c : b) {
		// System.out.println(c);
		// }
		// System.out.println("十进制10转16进制为" + Integer.toHexString(10));
		// String a = "aaaa";
		// byte[] b = a.getBytes();
		// System.out.println(b.length);
		// System.out.println(int2byte(121 + 2).length);

		// String a =
		// "{\"cmd\":\"FDLISTRS\",\"cometid\":\"241504547241984\",\"data\":[{\"friendid\":\"1009\",\"id\":1,\"remarkname\":\"墨恒\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1001\",\"id\":2,\"remarkname\":\"展坑\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1003\",\"id\":3,\"remarkname\":\"绵羊\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1005\",\"id\":5,\"remarkname\":\"益达\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1006\",\"id\":6,\"remarkname\":\"乐视\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1007\",\"id\":7,\"remarkname\":\"小米\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1010\",\"id\":9,\"remarkname\":\"清风\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"},{\"friendid\":\"1004\",\"id\":10,\"remarkname\":\"\",\"sourcetype\":119,\"tabName\":\"nim_friend_10\",\"userid\":\"1002\"}],\"errorCode\":0,\"platform\":1,\"responseCode\":134217728,\"sessionid\":5,\"userid\":\"1002\"}";
		//
		// System.out.println(a.length());
		//
		// System.out.println(a.getBytes().length);
		//
		// System.out.println(intToByte4(a.length() + 2).length);
		//
		// byte[] aa = unsignedShortToByte2(a.length() + 2);
		// for (int i = 0; i < aa.length; i++) {
		// System.out.println(aa[i]);
		// }

		System.out.println(App.map.get("FDLISTRQ"));

	}

	public static byte[] intToByte4(int i) {
		byte[] targets = new byte[4];
		targets[3] = (byte) (i & 0xFF);
		targets[2] = (byte) (i >> 8 & 0xFF);
		targets[1] = (byte) (i >> 16 & 0xFF);
		targets[0] = (byte) (i >> 24 & 0xFF);
		return targets;
	}

	public static byte[] int2byte(int res) {
		byte[] targets = new byte[4];

		targets[0] = (byte) (res & 0xff);// 最低位
		targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
		targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
		targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
		return targets;
	}

	public static byte[] unsignedShortToByte2(int s) {
		byte[] targets = new byte[2];
		targets[0] = (byte) (s >> 8 & 0xFF);
		targets[1] = (byte) (s & 0xFF);
		return targets;
	}
}
