package com.chenhl.zk.test;

//import org.apache.curator.RetryPolicy;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.framework.state.ConnectionState;
//import org.apache.curator.framework.state.ConnectionStateListener;
//import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @创建人: chenhl
 * @创建时间: 2020/10/19
 * @描述: 连接状态监听
 */
public class CuratorDmo3 {

//    public static final String zk_addr="192.168.153.128:2181";
//    public static void main(String[] args) throws Exception {
//        // Zookeeper集群地址，多个节点地址可以用逗号分隔
//        String zkAddress = zk_addr;
//        // 重试策略，如果连接不上ZooKeeper集群，会重试三次，重试间隔会递增
//        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
//        // 创建Curator Client并启动，启动成功之后，就可以与Zookeeper进行交互了
//        CuratorFramework client = CuratorFrameworkFactory
//                .newClient(zkAddress, retryPolicy);
//        client.start();
//        // 添加ConnectionStateListener监听器
//        client.getConnectionStateListenable().addListener(
//                new ConnectionStateListener() {
//                    public void stateChanged(CuratorFramework client,
//                                             ConnectionState newState) {
//                        // 这里我们可以针对不同的连接状态进行特殊的处理
//                        switch (newState) {
//                            case CONNECTED:
//                                // 第一次成功连接到ZooKeeper之后会进入该状态。
//                                // 对于每个CuratorFramework对象，此状态仅出现一次
//                                System.out.println("CONNECTED");
//                                break;
//                            case SUSPENDED: //   ZooKeeper的连接丢失
//                                System.out.println("SUSPENDED");
//                                break;
//                            case RECONNECTED: // 丢失的连接被重新建立
//                                System.out.println("RECONNECTED");
//                                break;
//                            case LOST:
//                                System.out.println("LOST");
//                                // 当Curator认为会话已经过期时，则进入此状态
//                                break;
//                            case READ_ONLY: // 连接进入只读模式
//                                System.out.println("READ_ONLY");
//                                break;
//                        }
//                    }
//                });
//
//
//    }
}
