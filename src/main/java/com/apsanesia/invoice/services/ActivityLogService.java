/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.services;

import com.apsanesia.invoice.dao.ActivityLogDao;
import com.apsanesia.invoice.entity.ActivityLog;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Slamet Hariyono
 */
@Service
public class ActivityLogService {

    @Autowired
    private ActivityLogDao activityLogDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void log(String logMessage) {
        // suspend transaction yang sedang berjalan (tx1)
        // start transaction baru (tx2)
        // yang ada disini akan dijalankan dalam transaction baru (tx2)
        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivityTime(LocalDateTime.now());
        activityLog.setFeature(ActivityLog.Feature.PAYMENT);
        activityLog.setMessage(logMessage);
        activityLogDao.save(activityLog);
        // commit / rollback tx2
        // resume tx1
    }
}
