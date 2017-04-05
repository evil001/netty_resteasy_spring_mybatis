package com.qbao.im.message.start;

import com.qbao.im.message.server.MessageServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by tangxiaojun on 2017/3/27.
 */
public class MessageStart {

    Logger logger = LoggerFactory.getLogger(MessageStart.class);

    public static void main(String[] args) throws InterruptedException {
//        new Thread(()-> MessageServer.startMessageServer(8080)).start();
        MessageServer.startMessageServer(8081);
    }


}
