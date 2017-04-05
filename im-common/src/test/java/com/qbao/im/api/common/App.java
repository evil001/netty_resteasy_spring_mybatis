package com.qbao.im.api.common;

import com.google.flatbuffers.FlatBufferBuilder;
import com.qbao.im.api.common.flatbuf.Message;

import java.nio.ByteBuffer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        FlatBufferBuilder fbb = new FlatBufferBuilder(1);
        int messageNum1 = fbb.createString("发送一条消息");
        int netId = fbb.createString("1000001");
        int messaeSend = fbb.createString("张三");
        int messageReceive = fbb.createString("李四");

        Message.startMessage(fbb);
        Message.addNetId(fbb,netId);
        Message.addUserId(fbb,messaeSend);
        Message.addDest(fbb,messageReceive);
        Message.addContent(fbb,messageNum1);

        int endMessage = Message.endMessage(fbb);
        fbb.finish(endMessage);
        System.out.println("消息发送结束===========");

        ByteBuffer buf = fbb.dataBuffer();
        Message msg = Message.getRootAsMessage(buf);

        System.out.println("接受消息:"+msg.content());
        System.out.println("发送者编号:"+msg.userId());
        System.out.printf("接受者编号:"+msg.dest());
    }
}
