package com.example.demo_log_aop.utils;

import jakarta.servlet.http.HttpServletRequest;

public class URLBuilder {
    public static String build(HttpServletRequest httpRequest) {
        StringBuilder url = new StringBuilder();
        String requestURL = String.valueOf(httpRequest.getRequestURL());
        String requestParam = httpRequest.getQueryString();

        url.append(requestURL);
        if (requestParam != null) {
            url.append("?").append(requestParam);
        }

        return url.toString();
    }
}
