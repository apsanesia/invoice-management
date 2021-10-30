/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.apsanesia.invoice.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author Slamet Hariyono
 */
@Data
@Entity
public class VirtualAccountConfiguration extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "payment_provider_id")
    private PaymentProvider paymentProvider;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
    
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String code;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String name;
    
    @NotNull
    @NotEmpty
    @Min(0)
    private BigDecimal transactionFeeFlat;
    
    @NotNull
    @NotEmpty
    private Float transactionFeePercentage;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String companyPrefix;
    
    @NotNull
    @NotEmpty
    @Min(0)
    private Integer accountNumberLength;
}
