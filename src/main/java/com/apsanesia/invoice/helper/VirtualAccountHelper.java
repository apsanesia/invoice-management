/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.helper;

import com.apsanesia.invoice.dao.VirtualAccountDao;
import com.apsanesia.invoice.entity.PaymentProvider;
import com.apsanesia.invoice.entity.VirtualAccount;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Slamet Hariyono
 */
public class VirtualAccountHelper {

    public static VirtualAccount cekVaAda(VirtualAccountDao virtualAccountDao, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundExeption {
        Optional<VirtualAccount> optVa = virtualAccountDao.findByPaymentProviderAndCompanyIdAndAccountNumber(provider, companyId, accountNumber);
        if (!optVa.isPresent()) {
            throw new VirtualAccountNotFoundExeption("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] not found.");
        }
        VirtualAccount va=optVa  .get();
        return va;
    }
}
