package com.example.auth.first_serice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/indiidu")
public class IndividuController {

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String(param);
    }
    

}
