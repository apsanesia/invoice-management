/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.apsanesia.invoice.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class BankAccount extends BaseEntity{

    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String accountNumber;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String accountName;
    
    @NotNull
    @NotEmpty
    @Size(max = 255)
    private String branchName;
}
