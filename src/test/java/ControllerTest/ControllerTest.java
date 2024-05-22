package ControllerTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.Investify.model.InvestorInfo;
import com.Investify.repository.InvestorInfoRepository;

import testConfig.TestConfig;

import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

//@SpringBootTest
//@ContextConfiguration(classes = TestConfig.class)
public class ControllerTest {
	
	@Autowired
	InvestorInfoRepository investorInfoRepository;
	
//	@Disabled
	@Test
	@CsvSource({
		"Sumer Khan",
		"Nilesh Bhagat",
		"Arnav Kumar",
		"Naushad Shaikh",
		"Vipul Sharma",
		"Sunder Pichai"
	})
	public void testUsername(String name) {
		InvestorInfo user = new InvestorInfo();
		
		assertTrue(!user.getName().isEmpty());
		assertNotNull(investorInfoRepository.findByUsername(name));
	}
	
//	@Disabled
//	@Test
//	public void testSum() {
//		assertEquals(4,2+2);
//	}
//	
//	@Disabled
//	@ParameterizedTest
//	@CsvSource({
//		"1,1,2",
//		"2,4,3",
//		"4,3,7",
//		"1,5,7",
//		"4,4,8"
//	})
//	public void TestParam(int a, int b, int expected) {
//		assertEquals(expected,a+b);
//	}

	

}
