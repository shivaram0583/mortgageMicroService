package com.loan.mortgageMicroService.controller;


import com.loan.mortgageMicroService.Entity.Mortgage;
import com.loan.mortgageMicroService.service.MortgageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/mortgage/v1.0/loanService")
@CrossOrigin(origins = "http://localhost:3000")
public class MortgageController{

    @Autowired
    private MortgageService mortgageService;

    @GetMapping("/getMortgage")
    public ResponseEntity<List<Mortgage>> getMortgageController()
    {
        log.info("----Inside GET mortgage controller----");
        return mortgageService.getMortgageDetails();
    }

    @GetMapping("/getMortgage/{id}")
    public ResponseEntity<Mortgage> getMortgageByIdController(@PathVariable("id") Integer id)
    {
        log.info("----Inside GET mortgage by id controller----");
        return mortgageService.getMortgageDetailsById(id);
    }

    @GetMapping("/bank")
    public ResponseEntity<Map<String, List<Mortgage>>> getMortgageByBankController()
    {
        log.info("----Inside GET mortgage by bank controller----");
        return mortgageService.getBankDetails();
    }

    @PostMapping("/postMortgage")
    public ResponseEntity<Mortgage> postMortgageController(@Valid @RequestBody Mortgage mortgageModel)
    {
        ResponseEntity<Mortgage> responseEntity = null;
        log.info("----Inside POST mortgage controller----");
        responseEntity = mortgageService.postMortgageDetails(mortgageModel);
        return responseEntity;
    }

    @PutMapping("/updateMortgage/{id}")
    public ResponseEntity<Mortgage> updateMortgageController(@RequestBody Mortgage updateMortgageModel, @PathVariable("id") Integer id)
    {
        log.info("----Inside PUT mortgage controller----");
        return mortgageService.updateMortgage(updateMortgageModel, id);
    }

    @DeleteMapping("/deleteMortgage/{id}")
    public ResponseEntity<String> deleteMortgageDetails(@PathVariable("id") Integer id){
        log.info("----Inside DELETE method mortgage controller----");
        mortgageService.deleteMortgage(id);
        return new ResponseEntity<>("Record Delete!", HttpStatus.OK);
    }
}
