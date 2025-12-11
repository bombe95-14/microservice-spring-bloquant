package com.example.auth.first_serice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.first_serice.dto.request.IndividuRequestDto;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/indiidu")
public class IndividuController {

    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String(param);
    }

    @PostMapping("add")
    public ResponseEntity<?> createIndividual(@RequestBody IndividuRequestDto individuRequestDto) {
        //TODO: process POST request
        
        return ResponseEntity.ok(individuRequestDto);
    }
    
    
    

}
