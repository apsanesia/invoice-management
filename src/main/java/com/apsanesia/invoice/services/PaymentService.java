/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.services;

import com.apsanesia.invoice.dao.VirtualAccountDao;
import com.apsanesia.invoice.entity.PaymentProvider;
import com.apsanesia.invoice.entity.VirtualAccount;
import com.apsanesia.invoice.exceptions.VirtualAccountAlreadyPaidException;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import com.apsanesia.invoice.helper.VirtualAccountHelper;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Slamet Hariyono
 */
@Service
@Transactional
public class PaymentService {

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundExeption, VirtualAccountAlreadyPaidException {
        VirtualAccount va  = VirtualAccountHelper.cekVaAda(virtualAccountDao, provider, companyId, accountNumber);
        cekVaLunas(va, companyId, accountNumber, provider);

        // 3. Cek apakah amount > nilai tagihan
        // 4. Update status VA menjadi lunas
        // 5. Update status invoice menjadi lunas
        // 6. Insert ke tabel Payment
        // 7. Notifikasi (next fase)
    }

    private void cekVaLunas(VirtualAccount va, String companyId, String accountNumber, PaymentProvider provider) throws VirtualAccountAlreadyPaidException {
        if (va.getInvoice().getPaid()) {
            throw new VirtualAccountAlreadyPaidException("VA [" + companyId + "/" + accountNumber + "-" + provider.getCode() + "] already paid.");
        }
    }
}
