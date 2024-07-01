package com.Investify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Investify.model.StartUpInfo;

@Repository
public interface StartUpRepository extends JpaRepository<StartUpInfo, Long> {
	
	public List<StartUpInfo> findByCompanyName(String name);
	
	@Query("SELECT s FROM StartUpInfo s WHERE s.companyName LIKE %:name%")
    List<StartUpInfo> findByCompanyNameContaining(@Param("name") String name);

}
