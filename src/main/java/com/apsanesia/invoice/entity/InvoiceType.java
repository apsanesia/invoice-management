/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@SQLDelete(sql = "UPDATE invoice_type SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class InvoiceType extends BaseEntity {

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
    private String paymentType;

    @ManyToMany
    @JoinTable(
            name = "invoice_type_provider",
            joinColumns = @JoinColumn(name = "invoice_type_id"),
            inverseJoinColumns = @JoinColumn(name = "payment_provider_id")
    )
    private Set<PaymentProvider> paymentProviders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "invoice_type_configuration",
            joinColumns = @JoinColumn(name = "invoice_type_id"),
            inverseJoinColumns = @JoinColumn(name = "virtual_account_configuration_id")
    )
    private Set<VirtualAccountConfiguration> virtualAccountConfigurations = new HashSet<>();
}
