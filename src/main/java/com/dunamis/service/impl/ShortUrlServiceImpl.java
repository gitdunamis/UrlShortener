package com.dunamis.service.impl;

import com.dunamis.MyPropertyConfig;
import com.dunamis.dao.IUrlDao;
import com.dunamis.domain.Url;
import com.dunamis.service.IShortUrlService;
import com.dunamis.util.Constant;
import com.dunamis.util.HashFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShortUrlServiceImpl implements IShortUrlService {

    private IUrlDao h2UrlDao;

    @Autowired
    MyPropertyConfig propertyConfig;

    public ShortUrlServiceImpl() {
        this.h2UrlDao = propertyConfig.getUrlDao();
    }


    @Override
    public String genShortUrl(String longUrl, String prefix) {
        //TODO: get the last index on the db and increment by 1
        var index = h2UrlDao.getLastIndex() + 1;

        String shortUrl;

        //generate a base62 encoding base on the index
        String key = HashFunctions.encodeBase62(index);
        if (prefix != null && !prefix.isBlank()) {//prefix supplied
             shortUrl = prefix + "-" + key;
        }
        //prefix not supplied
        else {
            shortUrl = key;
        }
        //TODO: check if db already has longUrl

        //TODO: insert a new url into db async
        var url = new Url();
        url.setUrlId(String.valueOf(index));
        url.setUniqueKey(shortUrl);
        url.setLongUrl(longUrl);
        h2UrlDao.createUrl(url);

        return Constant.DOMAIN_NAME + shortUrl;
    }

    @Override
    public String getLongUrlFromShortUrl(String shortUrl) {
        //get base10 index from base62 encoded `shortUrl`
        var index = getUrlIndexFromShortUrl(shortUrl);

        //TODO: check db for index
        var longUrl = h2UrlDao.getUrlByIndex(index);

        //TODO: return long url at index
        return longUrl.getLongUrl();
    }

    private int getUrlIndexFromShortUrl(String shortUrl) {
        var prefixIndex = shortUrl.indexOf("-"); //get the index of - if it's present
        //url has no prefix
        if (prefixIndex >= 0) { // url has prefix
            return  HashFunctions.decodeBase62(shortUrl.substring(prefixIndex+1));
        }
        //url has no prefix
        else return HashFunctions.decodeBase62(shortUrl);
    }
}
