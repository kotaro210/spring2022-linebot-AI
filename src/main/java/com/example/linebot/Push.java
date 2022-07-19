package com.example.linebot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * (動作確認用)
 */
@RestController
public class Push {

    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

}

