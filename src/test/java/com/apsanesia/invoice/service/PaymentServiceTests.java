/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.apsanesia.invoice.service;

import com.apsanesia.invoice.exceptions.VirtualAccountAlreadyPaidException;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import com.apsanesia.invoice.services.PaymentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Slamet Hariyono
 */
public class PaymentServiceTests {
    @Autowired
    private PaymentService paymentService;
    
    @Test
    public void testPayment() throws VirtualAccountNotFoundExeption, VirtualAccountAlreadyPaidException{
        paymentService.pay(null, null, null, null, null);
    }
}
