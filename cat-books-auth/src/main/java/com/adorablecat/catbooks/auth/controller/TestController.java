package com.adorablecat.catbooks.auth.controller;

import com.adorablecat.response.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liuRun
 * @date 2025/8/20 16:30
 * @description TestController
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public Response<String> test() {
        return Response.success("Hello, 犬小哈专栏");
    }
}
