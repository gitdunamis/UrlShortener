package com.dunamis.dao;


import com.dunamis.domain.Url;

public interface IUrlDao {
    void createUrl(Url url);

    Url getUrlByIndex(int index);

    int deleteUrl(int index);

    long updateUrl(Url url);

    long getLastIndex();
}
