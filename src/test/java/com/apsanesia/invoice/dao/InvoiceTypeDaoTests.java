/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.dao;

import com.apsanesia.invoice.entity.InvoiceType;
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
    "/sql/delete-invoice-type.sql",
    "/sql/insert-inactive-invoice-type.sql"
})
public class InvoiceTypeDaoTests {

    @Autowired
    InvoiceTypeDao invoiceTypeDao;

    @Test
    public void testInsertInvoiceType() throws InterruptedException {
        InvoiceType it = new InvoiceType();
        it.setCode("IT-001");
        it.setName("Invoice Type Test");
        Assertions.assertNull(it.getId());
        invoiceTypeDao.save(it);
        Assertions.assertNotNull(it.getId());
        System.out.println("ID: " + it.getId());
        System.out.println("Created Time: " + it.getCreated());
        System.out.println("Created By: " + it.getCreatedBy());
        System.out.println("Updated Time: " + it.getUpdated());
        System.out.println("Updated By: " + it.getUpdatedBy());
        System.out.println("Status Record: " + it.getStatusRecord());
        System.out.println("=================================================================================================");
        Thread.sleep(1000);
        it.setName("Test Update");
        it = invoiceTypeDao.save(it);
        System.out.println("Created Time: " + it.getCreated());
        System.out.println("Updated Time: " + it.getUpdated());
        Assertions.assertNotEquals(it.getCreated(), it.getUpdated());
    }

    @Test
    public void testQuerySoftDelete() {
        Long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Record: " + jumlahRecord);
        Assertions.assertEquals(1, jumlahRecord);
    }

    @Test
    public void testSoftDelete() {
        InvoiceType invoiceType = invoiceTypeDao.findById("test002").get();
        invoiceTypeDao.delete(invoiceType);
        Long jumlahRecord = invoiceTypeDao.count();
        System.out.println("Jumlah Record: " + jumlahRecord);
        Assertions.assertEquals(0, jumlahRecord);
    }
}
