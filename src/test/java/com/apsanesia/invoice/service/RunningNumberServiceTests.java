/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.service;

import com.apsanesia.invoice.services.RunningNumberService;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Slamet Hariyono
 */
@SpringBootTest
public class RunningNumberServiceTests {

    @Autowired
    private RunningNumberService runningNumberService;

    @Test
    public void testGetNumber() {
        Long hasil = runningNumberService.getNumber("Test");
        Assertions.assertNotNull(hasil);
        System.out.println("Hasil: " + hasil);
    }

    @Test
    public void testGetNumberMultithread() throws InterruptedException {
        int jumlahThread = 10;
        final int iterasi = 5;
        ConcurrentHashMap hasilMap = new ConcurrentHashMap();
        for (int i = 0; i < jumlahThread; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Math.abs(new Random().nextInt(1000)));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(RunningNumberServiceTests.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    for (int j = 0; j < iterasi; j++) {
                        List<Long> lastNumbers = (List<Long>) hasilMap.get(this.getId());
                        if (lastNumbers == null) {
                            lastNumbers = new ArrayList<>();
                        }
                        Long hasil = runningNumberService.getNumber("Test");
                        //Assertions.assertNotNull(hasil);
                        System.out.println("Thread: [" + this.getId() + "] last: " + hasil);
                        lastNumbers.add(hasil);
                        hasilMap.put(this.getId(), lastNumbers);
                    }
                }
            };
            t.start();
        }
        Thread.sleep(10 * 1000);
        Enumeration<Long> keys = hasilMap.keys();
        while (keys.hasMoreElements()) {
            Long nextElement = keys.nextElement();
            System.out.println("==== Thread: " + nextElement + "============");
            System.out.println(hasilMap.get(nextElement));
        }
    }
}
