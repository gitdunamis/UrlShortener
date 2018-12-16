package com.dunamis.domain;

import lombok.Data;

@Data
public class Url {
    private String urlId;
    private String longUrl;
    private String uniqueKey;
    private String validityPeriod;
    private int maxNumHits;
}
