/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.apsanesia.invoice.services;

import com.apsanesia.invoice.dao.InvoiceDao;
import com.apsanesia.invoice.dao.VirtualAccountDao;
import com.apsanesia.invoice.entity.Invoice;
import com.apsanesia.invoice.entity.PaymentProvider;
import com.apsanesia.invoice.entity.PaymentStatus;
import com.apsanesia.invoice.entity.StatusRecord;
import com.apsanesia.invoice.entity.VirtualAccount;
import com.apsanesia.invoice.exceptions.PaymentExceedInvoiceAmountException;
import com.apsanesia.invoice.exceptions.VirtualAccountAlreadyPaidException;
import com.apsanesia.invoice.exceptions.VirtualAccountNotFoundExeption;
import com.apsanesia.invoice.helper.VirtualAccountHelper;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Slamet Hariyono
 */
@Service
@Transactional(rollbackFor = {
    VirtualAccountNotFoundExeption.class,
    VirtualAccountAlreadyPaidException.class,
    PaymentExceedInvoiceAmountException.class
})
public class PaymentService {

    @Autowired
    private ActivityLogService activityLogService;

    @Autowired
    private VirtualAccountDao virtualAccountDao;

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private VirtualAccountHelper virtualAccountHelper;

    @Transactional(rollbackFor = {
        VirtualAccountNotFoundExeption.class,
        VirtualAccountAlreadyPaidException.class,
        PaymentExceedInvoiceAmountException.class
    })
    public void pay(PaymentProvider provider, String companyId, String accountNumber, BigDecimal amount, String reference) throws VirtualAccountNotFoundExeption, VirtualAccountAlreadyPaidException, PaymentExceedInvoiceAmountException {
        //begin tx1
        activityLogService.log("Start payment VA " + accountNumber); // suspend tx1
        VirtualAccount va  = virtualAccountHelper.getExistingVirtualAccount(virtualAccountDao, provider, companyId, accountNumber);
        virtualAccountHelper.checkVaAlreadyPaid(va);
        virtualAccountHelper.checkPaymentAmount(va, amount);

        // 3. Cek apakah amount > nilai tagihan
        // 4. Update status VA menjadi lunas
        va.setStatusRecord(StatusRecord.INACTIVE);
        virtualAccountDao.save(va);
        // 5. Update status invoice menjadi lunas
        Invoice invoice = va.getInvoice();
        invoice.setTotalPayment(invoice.getTotalPayment().add(amount));
        invoice.setPaymentStatus(PaymentStatus.FULL);
        invoiceDao.save(invoice);
        // 6. Insert ke tabel Payment
        // 7. Notifikasi (next fase)
        // commit tx1

        throw new VirtualAccountAlreadyPaidException();
    }

    public void undoPayment() {

    }
}
