package com.sparta.msa_exam.product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ProductHeaderInterceptor implements HandlerInterceptor {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) {
        // Manipulate response after controller method has been executed
        response.addHeader("Server-port", serverPort);
    }
}
