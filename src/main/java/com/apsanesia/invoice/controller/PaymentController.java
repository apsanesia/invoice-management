/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.apsanesia.invoice.controller;

import com.apsanesia.invoice.exceptions.VirtualAccountAlreadyPaidException;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import com.apsanesia.invoice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Slamet Hariyono
 */
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void pay() throws VirtualAccountNotFoundExeption, VirtualAccountAlreadyPaidException{
        //paymentService.pay(null, null, null, null, null);
    }
}
