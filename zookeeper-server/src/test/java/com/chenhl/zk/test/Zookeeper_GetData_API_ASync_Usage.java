package com.chenhl.zk.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Think on 2017/7/8.
 */
public class Zookeeper_GetData_API_ASync_Usage implements Watcher {

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zk = null;

    public static void main(String[] args) throws Exception {
        String path = "/zk-test2";
        zk = new ZooKeeper("192.168.2.12:2181", 5000,
                new Zookeeper_GetData_API_ASync_Usage());

        countDownLatch.await();

        zk.create(path, "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);

        zk.getData(path, true, new IDataCallback(), null);

        zk.setData(path, "124".getBytes(), -1);
        Thread.sleep(Integer.MAX_VALUE);
    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) {
                try {
                    zk.getData(event.getPath(), true, new IDataCallback(), null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class IDataCallback implements AsyncCallback.DataCallback{
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        System.out.println(rc + ", " + path +", " + new String(data));
        System.out.println(stat.getCzxid() + ", " + stat.getMzxid() + ", " +stat.getVersion());
    }
}