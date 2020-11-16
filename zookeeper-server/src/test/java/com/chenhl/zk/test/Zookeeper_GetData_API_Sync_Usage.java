package com.chenhl.zk.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Think on 2017/7/8.
 */
public class Zookeeper_GetData_API_Sync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    private static Stat stat = new Stat();

    public static void main(String[] args) throws Exception {
        String path = "/zk-test1";
        zk = new ZooKeeper("192.168.2.12:2181", 5000,
                new Zookeeper_GetData_API_Sync_Usage());

        countDownLatch.await();

        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        System.out.println(new String(zk.getData(path, true, stat)));
        System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " +
                stat.getVersion());

        zk.setData(path, "124".getBytes(), -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println(new String(zk.getData(event.getPath(), true, stat)));
                    System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " +
                            stat.getVersion());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
