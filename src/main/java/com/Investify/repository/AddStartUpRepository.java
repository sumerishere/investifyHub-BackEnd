package com.Investify.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Investify.model.AddStartUp;

@Repository
public interface AddStartUpRepository extends JpaRepository<AddStartUp,Long> {
	

}
