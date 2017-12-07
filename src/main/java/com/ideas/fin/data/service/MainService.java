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
        saleforceReportsByName.entrySet().forEach(entry ->{
           entry.getValue().forEach(v->{
               CashReceipts invoice = cashReceiptService.getRecieptOfCustomerForType(v.getOpportunityName(),v.getAccountNumber(),"Sales / Invoices");
               CashReceipts payments = cashReceiptService.getRecieptOfCustomerForType(v.getOpportunityName(),v.getAccountNumber(),"Payments");
               if(invoice!=null && payments!=null){
                   Date accessDate = v.getAccessDate();
                   Date invoiceDate = invoice.getDocumentDate();
                   int year = getYearsBetweenDates(accessDate,invoiceDate);
                   double commissionFactor = getCommisionFactor(year,v.getBusinessModel());
                   double paymentAmount = payments.getOriginatingTrxAmount()!=null?payments.getOriginatingTrxAmount():payments.getTrxAmount();
                   double liableAmount = paymentAmount*commissionFactor;
               }

           });
        });
        return null;
    }

    private double getCommisionFactor(int year, SaleforceReport.BusinessModel businessModel) {
        TreeMap<Integer, Double> businessModelMap = new TreeMap<>();
        businessModelMap.put(1,businessModel.getFirstYear());
        businessModelMap.put(2,businessModel.getSecondYear());
        businessModelMap.put(3,businessModel.getOngoing());
        businessModelMap.put(0,businessModel.getConsulting());
        return businessModelMap.floorEntry(year).getValue();
    }

    public int getYearsBetweenDates(Date start, Date end) {
        if (start.before(end)) {
            return ((Long) ((ChronoUnit.DAYS.between(getLocalDateFromDate(start), getLocalDateFromDate(end)) + 1L) / 365L)).intValue()+1;
        }
        return 0;
    }


    public static LocalDate getLocalDateFromDate(Date date) {
        return date.toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
    }
}
