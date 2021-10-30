/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apsanesia.invoice.dao;

import com.apsanesia.invoice.entity.ActivityLog;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Banjaranyar
 */
public interface ActivityLogDao extends CrudRepository<ActivityLog, String>{
    
}
