package com.Investify.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Investify.model.InvestorInfo;
import com.Investify.model.StartUpInfo;

@Repository
public interface InvestorInfoRepository extends JpaRepository<InvestorInfo,Long> {
	
	public Optional<InvestorInfo> findByUsernameAndPassword(String username, String password);
	
	public Optional<InvestorInfo> findByUsername(String username);

	public Optional<InvestorInfo> findByMailId(String mailId);
	
	
	
	@Query("SELECT s FROM InvestorInfo s WHERE s.name LIKE %:name%")
    List<InvestorInfo> findByInvestorNameContaining(@Param("name") String name);

}
