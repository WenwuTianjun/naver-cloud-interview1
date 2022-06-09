package com.naver.cloud;

import org.junit.Test;

public class TestCacheData {

    @Test
    public void testProcessCachedData() {
        CachedData cachedData = new CachedData();
        cachedData.put("hello", "world");
        System.out.println(cachedData.get("hello"));
    }

    @Test
    public void testMultiThreadProcessCachedData() {
        CachedData cachedData = new CachedData();

        for (int i = 0; i < 3; i++) {
            final int temp = i;
            new Thread(() -> {
                cachedData.put("hello" + temp, "world" + temp);
                System.out.println(cachedData.get("hello" + temp));
            }).start();
        }
    }
}
