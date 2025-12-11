package com.example.auth.first_serice.service;

import org.springframework.stereotype.Service;

import com.example.auth.first_serice.repository.IndividuRepository;

@Service
public class IndividuService {

        private final IndividuRepository individuRepository;

        
    public IndividuService(IndividuRepository individuRepository){
        this.individuRepository = individuRepository;
    }

}
