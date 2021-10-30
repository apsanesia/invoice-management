/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.service;

import com.apsanesia.invoice.dao.CustomerDao;
import com.apsanesia.invoice.dao.InvoiceTypeDao;
import com.apsanesia.invoice.dao.VirtualAccountDao;
import com.apsanesia.invoice.entity.Customer;
import com.apsanesia.invoice.entity.Invoice;
import com.apsanesia.invoice.entity.InvoiceType;
import com.apsanesia.invoice.entity.VirtualAccount;
import com.apsanesia.invoice.entity.VirtualAccountConfiguration;
import com.apsanesia.invoice.services.InvoiceService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author Slamet Hariyono
 */
@SpringBootTest
@Sql(scripts = {
    "classpath:/sql/delete-sample-data-invoice.sql",
    "classpath:/sql/insert-sample-data-invoice.sql"
})
public class InvoiceServiceTests {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private InvoiceTypeDao invoiceTypeDao;

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void createInvoice() {
        Customer customer = customerDao.findById("c001").get();
        InvoiceType registrasi = invoiceTypeDao.findById("registrasi").get();
        BigDecimal amount = new BigDecimal(123000.98);
        String description = "Tagihan Registrasi";
        Invoice invoice = invoiceService.createInvoice(customer, registrasi, description, amount);
        VirtualAccountConfiguration config = new VirtualAccountConfiguration();
        config.setId("va-bni");
        VirtualAccount virtualAccount = new VirtualAccount();
        virtualAccount.setInvoice(invoice);
        virtualAccount.setAccountNumber("12345");
        virtualAccount.setVirtualAccountConfiguration(config);
        virtualAccount.setCreated(LocalDateTime.now());
        virtualAccount.setCreatedBy("Test");
        virtualAccountDao.save(virtualAccount);
        Assertions.assertNotNull(invoice);
        Assertions.assertNotNull(invoice.getInvoiceNumber());
        System.out.println("==================================================");
        System.out.println("Invoice Number: " + invoice.getInvoiceNumber());
        System.out.println("==================================================");
    }
}
