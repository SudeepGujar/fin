package com.ideas.fin.data.entity;

import java.util.Date;

public class Liability {
    public String accountNumber;
    public String hotelName;
    public String businessModel;
    public int year;
    public Double model;
    public String invoiceNumber;
    public Date invoiceDate;
    public Double clientInvoiceAmount;
    public Double fullCommissionAmount;
    public Date paidDate;
    public Double paidAmount;
    public Double credittoClient;
    public Double clientWithholdingTaxAmount;
    public Double originalAmountPaidToPartner;
    public String partner;

    public Liability( String accountNumber, String hotelName, String businessModel, int year, Double model, String invoiceNumber, Date invoiceDate, Double clientInvoiceAmount, Double fullCommissionAmount, Date paidDate, Double paidAmount, Double originalAmountPaidToPartner, String partnerName) {
        this.accountNumber = accountNumber;
        this.hotelName = hotelName;
        this.businessModel = businessModel;
        this.year = year;
        this.model = model;
        this.invoiceNumber = invoiceNumber;
        this.invoiceDate = invoiceDate;
        this.clientInvoiceAmount = clientInvoiceAmount;
        this.fullCommissionAmount = fullCommissionAmount;
        this.paidDate = paidDate;
        this.paidAmount = paidAmount;
        this.originalAmountPaidToPartner = originalAmountPaidToPartner;
        this.partner = partnerName;
    }
}
