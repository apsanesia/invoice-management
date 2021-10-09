/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
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
@SQLDelete(sql = "UPDATE invoice SET status_record = 'INACTIVE' WHERE id = ?")
@Where(clause = "status_record = 'ACTIVE'")
public class Invoice extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "invoice_type_id")
    private InvoiceType invoiceType;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String invoiceNumber;

    @NotNull
    private LocalDate dueDate;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String description;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    private Boolean paid = false;

}
