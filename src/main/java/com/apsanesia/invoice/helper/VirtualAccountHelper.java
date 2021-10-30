/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.helper;

import com.apsanesia.invoice.dao.VirtualAccountDao;
import com.apsanesia.invoice.entity.PaymentProvider;
import com.apsanesia.invoice.entity.VirtualAccount;
import com.apsanesia.invoice.exceptions.PaymentExceedInvoiceAmountException;
import com.apsanesia.invoice.exceptions.VirtualAccountAlreadyPaidException;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Slamet Hariyono
 */
@Service
public class VirtualAccountHelper {

    public VirtualAccount getExistingVirtualAccount(VirtualAccountDao virtualAccountDao, PaymentProvider provider, String companyId, String accountNumber) throws VirtualAccountNotFoundExeption {
        Optional<VirtualAccount> optVa = virtualAccountDao.findByAccountNumber(accountNumber);
        if (!optVa.isPresent()) {
            throw new VirtualAccountNotFoundExeption("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] not found.");
        }
        VirtualAccount va  = optVa.get();
        return va;
    }

    public void checkVaAlreadyPaid(VirtualAccount va) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("VA [" + va  + "] already paid.");
        }
    }

    public void checkPaymentAmount(VirtualAccount va, BigDecimal amount) throws PaymentExceedInvoiceAmountException {
        BigDecimal vaAmount = va.getInvoice().getAmount();
        if (vaAmount.longValue() < amount.longValue()) {
            throw new PaymentExceedInvoiceAmountException("");
        }
    }
}
