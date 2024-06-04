package com.Investify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Investify.model.StartUpInfo;

@Repository
public interface StartUpRepository extends JpaRepository<StartUpInfo, Long> {
	
	public List<StartUpInfo> findByCompanyName(String name);
	
//	public List<StartUpInfo> findAllByindustry(String industry);
	
}
