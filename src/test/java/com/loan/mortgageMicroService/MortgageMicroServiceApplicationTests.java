package com.loan.mortgageMicroService;

import com.loan.mortgageMicroService.Entity.Mortgage;
import com.loan.mortgageMicroService.repository.MortgageRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class MortgageMicroServiceApplicationTests {

	private RestTemplate restTemplate;

	String mortgageResourceUrl;

	@Autowired
	MortgageRepository mortgageRepository;

	@BeforeEach
	public void setUp()
	{
		mortgageResourceUrl = "http://localhost:8080/mortgage/v1.0/loanService";
		restTemplate = new RestTemplate();
	}
	 private void postMortgageDetails(Mortgage mortgage)
	 {
		 ResponseEntity<String> postMortgageDetails = restTemplate.postForEntity(mortgageResourceUrl + "/postMortgage", mortgage, String.class);
		 log.info(postMortgageDetails.getBody());
		 assertTrue(postMortgageDetails.getBody().contains("abc"));
	 }

	@Test
	void contextLoads() {
	}

}
