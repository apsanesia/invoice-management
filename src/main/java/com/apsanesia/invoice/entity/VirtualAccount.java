/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author Slamet Hariyono
 */
@Data
@Entity
@SQLDelete(sql = "UPDATE invoice SET virtual_accout = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class VirtualAccount extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "payment_provider_id")
    private PaymentProvider paymentProvider;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String companyId;

    @NotNull
    @NotEmpty
    @Size(min = 1)
    private String accountNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private VirtualAccountType virtualAccountType = VirtualAccountType.CLOSED;
}
