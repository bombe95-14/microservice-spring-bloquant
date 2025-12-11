package com.example.auth.first_serice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.auth.first_serice.entity.Individu;

@Repository
public interface IndividuRepository extends JpaRepository<Individu, Long>{

}
