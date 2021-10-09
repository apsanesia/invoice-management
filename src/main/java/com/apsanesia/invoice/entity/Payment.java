/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

/**
 *
 * @author Slamet Hariyono
 */
@Data
@Entity
public class Payment extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "virtual_account_id")
    private VirtualAccount virtualAccount;

    @NotNull
    private LocalDateTime transactionTime;

    @NotNull
    @NotEmpty
    private String providerReference;
}
