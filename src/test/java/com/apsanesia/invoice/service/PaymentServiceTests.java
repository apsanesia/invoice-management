/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.service;

import com.apsanesia.invoice.dao.VirtualAccountConfigurationDao;
import com.apsanesia.invoice.entity.PaymentProvider;
import com.apsanesia.invoice.entity.VirtualAccountConfiguration;
import com.apsanesia.invoice.exceptions.PaymentExceedInvoiceAmountException;
import com.apsanesia.invoice.exceptions.VirtualAccountAlreadyPaidException;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import com.apsanesia.invoice.services.PaymentService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Slamet Hariyono
 */
@SpringBootTest
public class PaymentServiceTests {

    @Autowired
    private VirtualAccountConfigurationDao virtualAccountConfigurationDao;

    @Autowired
    private PaymentService paymentService;

    @Test
    public void testPay() throws VirtualAccountNotFoundExeption, VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException {

        VirtualAccountConfiguration config = virtualAccountConfigurationDao.findById("va-bni").get();
        paymentService.pay(
                config.getPaymentProvider(),
                config.getCompanyPrefix(),
                "12345",
                new BigDecimal(123000.98),
                "abcd"
        );
    }
}
