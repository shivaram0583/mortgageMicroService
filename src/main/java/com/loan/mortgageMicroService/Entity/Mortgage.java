package com.loan.mortgageMicroService.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@DynamicUpdate
@Data
@Entity
@Table(name = "MORTGAGE")
public class Mortgage {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="ACCOUNT_NAME")
    @Size(min=2, message = "Account name should not be null..")
    @NotNull
    private String accountName;

    @Column(name="TYPE")
    @Size(min=2, message = "Type should not be null..")
    @NotNull
    private String type;

    @Column(name="PROVIDE_NAME")
    @Size(min=2, message = "Provider name should not be null..")
    @NotNull
    private String providerName;

    @NotNull
    @Column(name="AMOUNT")
    private BigDecimal amount;

    @NotNull
    @Column(name="CURRENCY")
    private String currency;

    @NotNull
    @Column(name="BALANCE_DATE")
    private LocalDate balanceDate;

    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "LAST_UPDATED")
    private Date lastUpdate;

    @PrePersist
    protected void onCreate(){
        createdDate = new Date();
        lastUpdate = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        lastUpdate = new Date();
    }

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "linkedProperty", column = @Column(name = "LINKED_PROPERTY")),
            @AttributeOverride(name = "monthlyRepayment", column = @Column(name = "MONTHLY_REPAYMENT")),
            @AttributeOverride(name = "term", column = @Column(name = "TERM")),
            @AttributeOverride(name = "apr", column = @Column(name = "APR")),
            @AttributeOverride(name = "interestType", column = @Column(name = "INTEREST_TYPE")),
            @AttributeOverride(name = "fixedDate", column = @Column(name = "FIXED_DATE"))})
    @JsonProperty("details")
    private Details details;
}
