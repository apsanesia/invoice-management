/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.services;

import com.apsanesia.invoice.dao.InvoiceDao;
import com.apsanesia.invoice.entity.Customer;
import com.apsanesia.invoice.entity.Invoice;
import com.apsanesia.invoice.entity.InvoiceType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Slamet Hariyono
 */
@Service
@Transactional
public class InvoiceService {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private RunningNumberService runningNumberService;

    public Invoice createInvoice() {
        Invoice invoice = new Invoice();
        return invoice;
    }

    public Invoice createInvoice(Customer customer, InvoiceType invoiceType, String description, BigDecimal amount) {
        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setInvoiceType(invoiceType);
        invoice.setDescription(description);
        invoice.setAmount(amount);
        invoice.setCreated(LocalDateTime.now());
        invoice.setCreatedBy("TEST USER");
        invoice.setUpdated(LocalDateTime.now());
        invoice.setUpdatedBy("TEST USER");
        invoice.setDueDate(LocalDate.now().plusMonths(1));
        invoice.setInvoiceNumber(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMdd")) + String.format("%03d", runningNumberService.getNumber("Invoice")));
        invoiceDao.save(invoice);
        return invoice;
    }

}
