package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.CashReceipts;
import com.ideas.fin.data.repository.CashReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CashReceiptService {
    private final CashReceiptRepository cashReceiptRepository;

    @Autowired
    public CashReceiptService(CashReceiptRepository cashReceiptRepository) {
        this.cashReceiptRepository = cashReceiptRepository;
    }

    public void save(List<List<Object>> cashReceiptRaw, String name, Date uploadDate) {
        final List<CashReceipts> cashReceipts = getCashReceipts(cashReceiptRaw, name, uploadDate);
        cashReceiptRepository.save(cashReceipts);
    }

    private List<CashReceipts> getCashReceipts(List<List<Object>> cashReceiptRaw, String name, Date uploadDate) {
        return cashReceiptRaw.stream()
                .map(objects -> new CashReceipts(objects.get(0).toString(), objects.get(1).toString(), objects.get(2).toString(), objects.get(3).toString(), (Date) objects.get(4), objects.get(5).toString(), (Double) objects.get(6), (Double) objects.get(7), name, uploadDate))
                .collect(Collectors.toList());
    }

    public CashReceipts getRecieptOfCustomerForType(String opportunityName, String accountNumber, String documentType) {
        return  cashReceiptRepository.findByCustomerNumberAndCustomerNameAndDocumentType(accountNumber,opportunityName,documentType);
    }
}
