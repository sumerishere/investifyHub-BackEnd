package com.Investify.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Investify.model.AddStartUp;
import com.Investify.model.InvestorInfo;

@Repository
public interface AddStartUpRepository extends JpaRepository<AddStartUp,Long> {
	
	boolean existsByStartupnameAndInvestorInfo(String startupname, InvestorInfo investorInfo);
	
	List<AddStartUp> findByInvestorInfoId(Long investorId);
	
	List<AddStartUp> findByInvestorInfo(InvestorInfo investorInfo);
	
	Optional<AddStartUp> findByStartupnameAndInvestorInfo(String startupname, InvestorInfo investorInfo);
}
