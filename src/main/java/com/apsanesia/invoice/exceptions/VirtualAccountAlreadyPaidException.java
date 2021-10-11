/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.exceptions;

/**
 *
 * @author Slamet Hariyono
 */

// jangan pakai RumtimeException supaya wajib dihandle oleh yang panggil
// RuntimeException diapaki untuk error yang tidak bisa dihandle oleh yang panggil
// contoh: query select one tapi balikannya lebih dari satu
public class VirtualAccountAlreadyPaidException extends Exception {

    public VirtualAccountAlreadyPaidException() {
    }

    public VirtualAccountAlreadyPaidException(String message) {
        super(message);
    }
}
