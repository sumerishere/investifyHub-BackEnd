package com.Investify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Investify.model.StartUpRegistraion;


@Repository
public interface StartUpRegistraionRepository extends JpaRepository<StartUpRegistraion, Long>{
	
}
