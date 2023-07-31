package com.innoventes.test.app.util;

import org.springframework.stereotype.Component;

import javax.servlet.http.*;
import java.util.stream.Collectors;

//@Component
//
//public class HandlerInterceptor implements org.springframework.web.servlet.HandlerInterceptor {
//    @Override
//    public booleanpreHandler(HttpServletRequest request, HttpServletResponse response, Object Handler) throws Exception{
//        if(request.getMethod().equals("POST")){
//            String payload= request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//
//            if(!isValidWebsiteURL(payload)){
//                String modifiedPayload=modifyPayload(payload);
//                request.setAttribute("modifiedPayload",modifiedPayload);
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServe)

//}
