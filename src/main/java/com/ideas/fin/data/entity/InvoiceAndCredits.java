package com.ideas.fin.data.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class InvoiceAndCredits {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    private final String CustomerNumber;
    private final String CustomerName;
    private final String DocumentNumber;
    private final String DocumentType;
    private final Date DocumentDate;
    private final String CurrencyID;
    private final Double SalesAmount;
    private final Double OriginatingSalesAmount;
    private final String filename;

    @PersistenceConstructor
    public InvoiceAndCredits(Long id, String customerNumber, String customerName, String documentNumber, String documentType, Date documentDate, String currencyID, Double salesAmount, Double originatingSalesAmount, String filename) {
        this.id = id;
        CustomerNumber = customerNumber;
        CustomerName = customerName;
        DocumentNumber = documentNumber;
        DocumentType = documentType;
        DocumentDate = documentDate;
        CurrencyID = currencyID;
        SalesAmount = salesAmount;
        OriginatingSalesAmount = originatingSalesAmount;
        this.filename = filename;
    }

    public InvoiceAndCredits(String customerNumber, String customerName, String documentNumber, String documentType, Date documentDate, String currencyID, Double salesAmount, Double originatingSalesAmount, String filename) {
        this(null, customerNumber, customerName, documentNumber, documentType, documentDate, currencyID, salesAmount, originatingSalesAmount, filename);
    }
}
