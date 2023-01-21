package com.ktxdevelopment.bailyapi.security;

import com.ktxdevelopment.bailyapi.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME= 864000000;
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING= "Authorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String HEADER_USER_ID = "USER_ID";

    public static String getTokenSecret() {
        AppProperties properties = (AppProperties) SpringApplicationContext.getBean("appProperties");
        return properties.getTokenSecret();
    }
}
