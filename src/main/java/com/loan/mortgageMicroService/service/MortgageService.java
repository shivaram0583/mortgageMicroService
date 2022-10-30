package com.loan.mortgageMicroService.service;

import com.loan.mortgageMicroService.Entity.Mortgage;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface MortgageService {

    ResponseEntity<List<Mortgage>> getMortgageDetails();

    ResponseEntity<Mortgage> getMortgageDetailsById(Integer id);

    ResponseEntity<Map<String, List<Mortgage>>> getBankDetails();

    ResponseEntity<Mortgage> postMortgageDetails(Mortgage mortgageModel);

    ResponseEntity<Mortgage> updateMortgage(Mortgage mortgageModel, Integer id);

    ResponseEntity<String> deleteMortgage(Integer id);

}
