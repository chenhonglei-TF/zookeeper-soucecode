package com.chenhl.zk.test;


import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Think on 2017/7/8.
 */
public class Zookeeper_Create_API_Sync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.1.111:2181", 5000,
                new Zookeeper_Create_API_Sync_Usage());
        countDownLatch.await();
        String path = zooKeeper.create("/zk-test-ephemeral-", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println("Success create znode: " + path);

        String path1 = zooKeeper.create("/zk-test-ephemeral-","".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("Success create znode: " + path1);

    }
}
