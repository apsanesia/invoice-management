/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apsanesia.invoice.dao;

import com.apsanesia.invoice.entity.RunningNumber;
import javax.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Banjaranyar
 */
public interface RunningNumberDao extends CrudRepository<RunningNumber, String> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    RunningNumber findByPrefix(String prefix);
}
