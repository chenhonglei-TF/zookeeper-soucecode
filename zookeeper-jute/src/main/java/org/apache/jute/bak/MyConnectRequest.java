package org.apache.jute.bak;

public class MyConnectRequest {

    private int protocolVersion;// 请求协议版本号
    private long lastZxidSeen;//最后一次接收到的服务器的zxid序号
    private int timeOut;//会话超时时间
    private long sessionId;//会话标识符
    private byte[] passwd;//会话密码
}
