/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.exceptions;

/**
 *
 * @author Slamet Hariyono
 */
public class PaymentExceedInvoiceAmountException extends Exception {

    public PaymentExceedInvoiceAmountException() {
    }

    public PaymentExceedInvoiceAmountException(String message) {
        super(message);
    }
}
