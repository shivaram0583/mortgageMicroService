package com.loan.mortgageMicroService.repository;

import com.loan.mortgageMicroService.Entity.Mortgage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MortgageRepository extends JpaRepository<Mortgage, Integer> {
}
