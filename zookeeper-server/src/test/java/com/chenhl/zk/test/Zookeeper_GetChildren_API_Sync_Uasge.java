package com.chenhl.zk.test;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Think on 2017/7/8.
 */
public class Zookeeper_GetChildren_API_Sync_Uasge implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);
    private static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws Exception {
        String path = "/zk-book";
        zooKeeper = new ZooKeeper("192.168.2.13:2181", 5000,
                new Zookeeper_GetChildren_API_Sync_Uasge());
        countDownLatch.await();

        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        zooKeeper.create(path + "/c1","".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        List<String> children = zooKeeper.getChildren(path, true);
        System.out.println(children);

        zooKeeper.create(path + "/c2", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Thread.sleep(Integer.MAX_VALUE);

    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None ==event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    System.out.println("ReGetChild: " + zooKeeper.getChildren(event.getPath(), true));
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
