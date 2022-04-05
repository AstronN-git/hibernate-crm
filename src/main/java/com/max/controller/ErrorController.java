package com.max.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {
    @GetMapping("/forbidden")
    public String forbiddenError() {
        return "/error/forbidden";
    }
}
