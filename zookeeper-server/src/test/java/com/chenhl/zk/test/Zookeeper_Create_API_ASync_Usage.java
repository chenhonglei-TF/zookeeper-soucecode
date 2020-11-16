package com.chenhl.zk.test;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Think on 2017/7/8.
 */
public class Zookeeper_Create_API_ASync_Usage implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState())
            countDownLatch.countDown();
    }

    public static void main(String[] args) throws Exception{
        ZooKeeper zooKeeper = new ZooKeeper("192.168.2.11:2181", 5000,
                new Zookeeper_Create_API_ASync_Usage());

        countDownLatch.await();

        zooKeeper.create("/zk-test-ephemeral-", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "I am context.");

        zooKeeper.create("/zk-test-ephemeral-", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "I am context.");

        zooKeeper.create("/zk-test-ephemeral-", "".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new IStringCallback(), "I am context.");

        Thread.sleep(Integer.MAX_VALUE);
    }
}

class IStringCallback implements AsyncCallback.StringCallback{

    public void processResult(int rc, String path, Object ctx, String name) {
        System.out.println("Create path result: [" + rc + ", " + path + ", " + ctx + ", real path name: " + name);
    }
}