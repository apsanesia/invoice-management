/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.exceptions;

/**
 *
 * @author Slamet Hariyono
 */
// checked exception
public class VirtualAccountNotFoundExeption extends Exception {

    public VirtualAccountNotFoundExeption() {
    }

    public VirtualAccountNotFoundExeption(String message) {
        super(message);
    }

}
