package com.loan.mortgageMicroService.service;

import com.loan.mortgageMicroService.Entity.Mortgage;
import com.loan.mortgageMicroService.exceptions.ResourceNotFoundException;
import com.loan.mortgageMicroService.repository.MortgageRepository;
import com.loan.mortgageMicroService.constants.Constants;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Slf4j
@Service
public class MortgageServiceImplementation implements MortgageService{

    @Autowired
    private MortgageRepository mortgageRepository;

    @Override
    public ResponseEntity<List<Mortgage>> getMortgageDetails() {
        log.info("----Before GET method service layer----");
        List<Mortgage> mortgage = new ArrayList<>();
        List<Mortgage> mortgagesList = mortgageRepository.findAll();
        {
            BigDecimal numberToSearch = new BigDecimal(20000);
            List<Mortgage> filterMortgageByAmount = mortgage.stream().filter(e -> e.getAmount().compareTo(numberToSearch) >= 450000).collect(Collectors.toCollection(ArrayList::new));
            System.out.println(filterMortgageByAmount);
        }
        log.info("----After GET method called service layer----");
        return new ResponseEntity<>(mortgagesList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Mortgage> getMortgageDetailsById(Integer id) {
        Mortgage mortgage = new Mortgage();
        try{
            log.info("----Before GET Mortgage By ID method service layer----");
            boolean isExists = mortgageRepository.existsById(id);
            if(isExists){
                log.info("----Inside GET Mortgage By ID method service layer: Mortgage exists----");
                mortgage = mortgageRepository.findById(id).get();
            }
            else {
                throw new ResourceNotFoundException("Mortgage with id "+id+" does not exists");
            }
        }
        catch (ResourceNotFoundException e){
            log.info("----Inside GET Mortgage By ID method service layer: Mortgage does not exists----");
            throw  new ResourceNotFoundException(e.getLocalizedMessage());
        }
        log.info("----After GET Mortgage By ID method service layer----");
        return ResponseEntity.ok(mortgage);
    }

    @Override
    public ResponseEntity<Map<String, List<Mortgage>>> getBankDetails() {

        log.info("----Before GET method inside bank details service layer----");
        List<Mortgage> mortgageByBank = mortgageRepository.findAll();
        Map<String, List<Mortgage>> groupByBank = mortgageByBank.stream().collect(Collectors.groupingBy(Mortgage::getAccountName));
        log.info("----After GET method inside bank details service layer----");
        return ResponseEntity.ok(groupByBank);
    }

    private Map<String, List<Mortgage>> groupByAccountName(List<Mortgage> mortgage)
    {
        Map<String, List<Mortgage>> mortgageByAccountName = new HashMap<>();
        List<Mortgage> mortgageList = new ArrayList<>();
        for(Mortgage mortgage1 : mortgage) {
            String accountName = mortgage1.getAccountName();
            if(mortgageByAccountName.containsKey(accountName)) {
                List<Mortgage> mortgages2 = mortgageByAccountName.get(accountName);
                mortgages2.add(mortgage1);
            } else{
                mortgageList.add(mortgage1);
                mortgageByAccountName.put(accountName, mortgageList);
            }
        }
        return mortgageByAccountName;
    }

    @Override
    public ResponseEntity<Mortgage> postMortgageDetails(Mortgage mortgageModel) {
        log.info("----Before POST method inside create mortgage service layer----");
        Integer id = mortgageModel.getId();
        try{
            if(mortgageRepository.existsById(id)){
                throw new ResourceNotFoundException("Mortgage details with id " + id + " already exists....");
            }
            mortgageRepository.save(mortgageModel);
        } catch (ResourceNotFoundException e) {
            log.info("----Inside POST moethod mortgage details already exists service layer----");
            throw new ResourceNotFoundException(e.getLocalizedMessage());
        }
        log.info("----After POST method inside create mortgage service layer----");
        return ResponseEntity.ok(mortgageModel);
    }

    @Override
    public ResponseEntity<Mortgage> updateMortgage(Mortgage newMortgageModel, Integer id) {
        try {
            log.info("----After PUT method in update mortgage service layer----");
            Integer idCheck = newMortgageModel.getId();
            if((id == idCheck) && mortgageRepository.existsById(idCheck)){
                Optional<Mortgage> mortgage = mortgageRepository.findById(id);
                if(mortgage.isPresent()){
                    log.info("----Inside PUT method mortgage details exists service layer----");
                    mortgageRepository.save(newMortgageModel);
                }
            }
            else {
                log.info("----Inside PUT method mortgage details does not exists service layer----");
                throw new ResourceNotFoundException("Mortgage details with id " +id+ " does not exists service layer");
            }
        }
        catch (Exception e)
        {
            log.info("----Inside PUT method mortgage not found service layer----");
            throw new ResourceNotFoundException("Mortgage NOT_FOUND...." + e.getLocalizedMessage());
        }
        log.info("----After PUT method in update mortgage service layer----");
        log.info(Constants.MORTGAGE_UPDATES_SAVES_WITH_NEW_VALUES);
        return ResponseEntity.ok(newMortgageModel);
    }

    @Override
    public ResponseEntity<String> deleteMortgage(Integer id) {
        log.info("----Before DELETE method inside delete mortgage service layer----");
        boolean isExists = mortgageRepository.existsById(id);
        if(!isExists)
        {
            log.info("----Inside DELETE method delete mortgage details not found service layer----");
            throw new ResourceNotFoundException("Mortgage with id "+id+" does not found.");
        }
        log.info("----After DELETE method inside delete mortgage service layer----");
        mortgageRepository.deleteById(id);
        return new ResponseEntity<>("Mortgage Record Deleted!", HttpStatus.OK);
    }
}
