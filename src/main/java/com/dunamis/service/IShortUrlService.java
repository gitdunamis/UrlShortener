package com.dunamis.service;

public interface IShortUrlService {

    //method that returns a new short url
    String genShortUrl(String loungUrl, String prefix);
    String getLongUrlFromShortUrl(String shortUrl);
}
