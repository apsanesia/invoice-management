/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.apsanesia.invoice.dao;

import com.apsanesia.invoice.entity.PaymentProvider;
import com.apsanesia.invoice.entity.VirtualAccount;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Banjaranyar
 */
public interface VirtualAccountDao extends PagingAndSortingRepository<VirtualAccount, String> {

    public Optional<VirtualAccount> findByPaymentProviderAndCompanyIdAndAccountNumber(PaymentProvider provider, String companyId, String accountNumber);

}
