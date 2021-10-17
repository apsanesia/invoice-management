/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.service;

import com.apsanesia.invoice.entity.Invoice;
import com.apsanesia.invoice.services.InvoiceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Slamet Hariyono
 */
@SpringBootTest
public class InvoiceServiceTests {

    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void createInvoice() {
        Invoice invoice = invoiceService.createInvoice();
        
    }
}
