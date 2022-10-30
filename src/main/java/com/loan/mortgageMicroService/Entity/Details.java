package com.loan.mortgageMicroService.Entity;

import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Date;


@Embeddable
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Details {

    private String linkedProperty;

    private double monthlyRepayment;

    private int term;

    private double apr;

    private String interestType;

    private LocalDate fixedDate;
}
