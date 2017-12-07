package com.ideas.fin.data.entity;

import java.util.Date;

public class Liability {
    public String accountNumber;
    public String HotelName;
    public Double model;
    public String invoice;
    public Date invoiceDate;
    public Double clientInvoiceAmount;
    public Double fullCommissionAmount;
    public Date paidDate;
    public Double paidAmount;
    public Double credittoClient;
    public Double clientWithholdingTaxAmount;
    public Double OriginalAmountPaidToPartner;

    public Liability(String accountNumber, String hotelName, Double model, String invoice, Date invoiceDate, Double clientInvoiceAmount, Double fullCommissionAmount, Date paidDate, Double paidAmount, Double originalAmountPaidToPartner) {
        this.accountNumber = accountNumber;
        HotelName = hotelName;
        this.model = model;
        this.invoice = invoice;
        this.invoiceDate = invoiceDate;
        this.clientInvoiceAmount = clientInvoiceAmount;
        this.fullCommissionAmount = fullCommissionAmount;
        this.paidDate = paidDate;
        this.paidAmount = paidAmount;
        OriginalAmountPaidToPartner = originalAmountPaidToPartner;
    }
}
