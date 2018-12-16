package com.dunamis.dao;

import com.dunamis.domain.Url;

public class RedisDao implements IUrlDao {
    @Override
    public void createUrl(Url url) {

    }

    @Override
    public Url getUrlByIndex(int index) {
        return null;
    }

    @Override
    public int deleteUrl(int index) {
        return 0;
    }

    @Override
    public long updateUrl(Url url) {
        return 0;
    }

    @Override
    public long getLastIndex() {
        return 0;
    }
}
