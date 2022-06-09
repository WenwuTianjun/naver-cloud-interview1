package com.naver.cloud;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Implement a cache class (CachedData) that caches the target data, and implement a method (processCachedData),
 * which process the data if the cache is valid, if not, first update the cache object according to its data source, then process it.
 * Requirements:
 * Use ReentrantReadWriteLock to implement above requirements.
 * Use lock downgrading
 */
public class CachedData {
    /**
     * 缓存基于Map实现
     */
    private volatile Map<String, Object> cache = null;
    /**
     * 使用读写锁来保证读写分离
     * 其中：
     * 读锁为共享锁，一个数据读取数据时，其他线程读取数据不受影响
     * 写锁为排他锁，一个线程写入数据时，其他线程读取数据不受影响，写入数据阻塞
     */
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 向缓存添加数据
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            if (cache == null) {
                cache = new HashMap<>();
            }
            cache.put(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 读取缓存数据
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        readWriteLock.readLock().lock();
        try {
            if (cache == null) {
                return null;
            }
            return cache.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Get Cache Data Error!");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    /**
     * 处理缓存数据
     *
     * @param key   键
     * @param value 值
     */
    public void processCachedData(String key, Object value) {
        this.put(key, value);
    }

}
