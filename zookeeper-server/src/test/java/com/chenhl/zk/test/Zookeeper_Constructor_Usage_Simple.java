package com.chenhl.zk.test;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

public class Zookeeper_Constructor_Usage_Simple implements Watcher {
    public static final String zk_addr="127.0.0.1:2181";
//    public static final String zk_addr="192.168.1.111:2181";
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public void process(WatchedEvent watchedEvent) {
        System.out.println("Receive watched event: " + watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            System.out.println("current Thread: " + Thread.currentThread().getName() + " countdown");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper = new ZooKeeper(zk_addr,
                12000000, new Zookeeper_Constructor_Usage_Simple());

        System.out.println(zooKeeper.getState());

        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Zookeeper session established.");

        System.in.read();
    }

}
