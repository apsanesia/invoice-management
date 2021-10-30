/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apsanesia.invoice.dao;

import com.apsanesia.invoice.entity.Invoice;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Banjaranyar
 */
public interface InvoiceDao extends PagingAndSortingRepository<Invoice, String>{
    
}
