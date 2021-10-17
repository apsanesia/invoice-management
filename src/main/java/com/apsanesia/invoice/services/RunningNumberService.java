/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.services;

import com.apsanesia.invoice.dao.RunningNumberDao;
import com.apsanesia.invoice.entity.RunningNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Slamet Hariyono
 */
@Service
@Transactional
public class RunningNumberService {

    @Autowired
    private RunningNumberDao runningNumberDao;

    public Long getNumber(String prefix) {
        RunningNumber runningNumber = runningNumberDao.findByPrefix(prefix);
        if (runningNumber == null) {
            runningNumber = new RunningNumber();
            runningNumber.setPrefix(prefix);
        }
        runningNumber.setLastNumber(runningNumber.getLastNumber() + 1);
        runningNumberDao.save(runningNumber);
        return runningNumber.getLastNumber();
    }

}
