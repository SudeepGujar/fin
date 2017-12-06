package com.ideas.fin.data.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class CashReceipts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    private final String customerNumber;
    private final String customerName;
    private final String documentNumber;
    private final String documentType;
    private final Date documentDate;
    private final String currencyID;
    private final Double trxAmount;
    private final Double originatingTrxAmount;
    private final String filename;
    private final Date uploadDate;

    @PersistenceConstructor
    public CashReceipts(Long id, String customerNumber, String customerName, String documentNumber, String documentType, Date documentDate, String currencyID, Double trxAmount, Double originatingTrxAmount, String filename, Date uploadDate) {
        this.id = id;

        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.documentNumber = documentNumber;
        this.documentType = documentType;
        this.documentDate = documentDate;
        this.currencyID = currencyID;
        this.trxAmount = trxAmount;
        this.originatingTrxAmount = originatingTrxAmount;
        this.filename = filename;
        this.uploadDate = uploadDate;
    }

    public CashReceipts(String customerNumber, String customerName, String documentNumber, String documentType, Date documentDate, String currencyID, Double trxAmount, Double originatingTrxAmount, String filename, Date uploadDate) {
        this(null, customerNumber, customerName, documentNumber, documentType, documentDate, currencyID, trxAmount, originatingTrxAmount, filename, uploadDate);
    }
}
