package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.CashReceipts;
import com.ideas.fin.data.entity.Liability;
import com.ideas.fin.data.entity.SaleforceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final CashReceiptService cashReceiptService;
    private final SaleForceReportService saleForceReportService;

    @Autowired
    public MainService(CashReceiptService cashReceiptService, SaleForceReportService saleForceReportService) {
        this.cashReceiptService = cashReceiptService;
        this.saleForceReportService = saleForceReportService;
    }

    public List<Liability> getLiabilities() {
        List<Liability> liabilities = new ArrayList<>();
        List<SaleforceReport> saleforceReports = saleForceReportService.getAllPartners();
        Map<String, List<SaleforceReport>> saleforceReportsByName = saleforceReports.stream().collect(Collectors.groupingBy(r -> r.getPartnerName()));
        saleforceReportsByName.entrySet().forEach(entry -> {
            entry.getValue().forEach(v -> {
                CashReceipts invoice = cashReceiptService.getRecieptOfCustomerForType(v.getAccountName(), v.getAccountNumber(), "Sales / Invoices");
                CashReceipts payments = cashReceiptService.getRecieptOfCustomerForType(v.getAccountName(), v.getAccountNumber(), "Payments");
                if (invoice != null) {
                    Date accessDate = v.getAccessDate();
                    Date invoiceDate = invoice.getDocumentDate();
                    int year = getYearsBetweenDates(accessDate, invoiceDate);
                    double commissionFactor = getCommisionFactor(year, v.getBusinessModel());
                    double clientinvoiceAmount = !invoice.getOriginatingTrxAmount().equals(0D) ? invoice.getOriginatingTrxAmount() : invoice.getTrxAmount();
                    double fullCommissionAmount = clientinvoiceAmount * commissionFactor / 100;
                    double paymentAmount = 0;
                    double liableAmount = 0;
                    Date documentDate = null;
                    if (payments != null) {
                        paymentAmount = !payments.getOriginatingTrxAmount().equals(0D) ? payments.getOriginatingTrxAmount() : payments.getTrxAmount();
                        liableAmount = paymentAmount * commissionFactor / 100;

                        documentDate = payments.getDocumentDate();
                    }
                    Liability liability = new Liability(v.getAccountNumber(), v.getAccountName(), v.getBusinessModelAsString(),year,commissionFactor, invoice.getDocumentNumber(),
                            invoiceDate, clientinvoiceAmount, fullCommissionAmount, documentDate, paymentAmount, liableAmount, v.getPartnerName());
                    liabilities.add(liability);
                }

            });
        });
        return liabilities;
    }

    private double getCommisionFactor(int year, SaleforceReport.BusinessModel businessModel) {
        TreeMap<Integer, Double> businessModelMap = new TreeMap<>();
        businessModelMap.put(1, businessModel.getFirstYear());
        businessModelMap.put(2, businessModel.getSecondYear());
        businessModelMap.put(3, businessModel.getOngoing());
        businessModelMap.put(0, businessModel.getConsulting());
        return businessModelMap.floorEntry(year).getValue();
    }

    public int getYearsBetweenDates(Date start, Date end) {
        if (start == null) {
            return 0;
        }
        if (start.before(end)) {
            return ((Long) ((ChronoUnit.DAYS.between(getLocalDateFromDate(start), getLocalDateFromDate(end)) + 1L) / 365L)).intValue() + 1;
        }
        return 0;
    }


    public static LocalDate getLocalDateFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
    }
}
