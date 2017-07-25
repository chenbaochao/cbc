package com.cbc.web;

import com.cbc.utils.SecurityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class DemoRestController {
    @GetMapping("/hello")
    public String greeting() {
        return "Hello World"+ SecurityUtils.getCurrentUser();
    }


    @GetMapping("/public/api")
    public String publicAPI() {
        return "this is public API";
    }
}