package com.dunamis.api.model;


import lombok.Data;

@Data
public class CreateResponse {
    private String statusCode;
    private String shortUrl;
    private String message;
}
