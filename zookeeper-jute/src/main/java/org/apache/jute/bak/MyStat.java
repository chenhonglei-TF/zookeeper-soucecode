package org.apache.jute.bak;

public class MyStat {

    private long czxid;//表示该数据节点被创建时的事务ID
    private long mzxid;//表示该节点最后一次被更新时的事务ID
    private long pzxid;//表示该节点的子节点列表最后一次被更新时的事务ID
    private long ctime;//表示该数据节点的创建时间
    private long mtime;//表示该节点最后一次被更新时的时间
    private int version;// 数据节点的版本号
    private int cversion;//子节点的版本号
    private int aversion;//节点的ACL版本号
    private long ephemeralOwner;//创建该临时节点的会话sessionID，如果是持久节点，那么这个属性值为0
    private int dataLength;//数据内容的长度
    private int numChildren;//当前节点的子节点个数

}
