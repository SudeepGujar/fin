package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.Liability;
import com.ideas.fin.data.entity.SaleforceReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class MainService {

    private final CashReceiptService cashReceiptService;
    private final InvoiceAndCreditsService invoiceAndCreditsService;
    private final SaleForceReportService saleForceReportService;

    @Autowired
    public MainService(CashReceiptService cashReceiptService, InvoiceAndCreditsService invoiceAndCreditsService, SaleForceReportService saleForceReportService) {
        this.cashReceiptService = cashReceiptService;
        this.invoiceAndCreditsService = invoiceAndCreditsService;
        this.saleForceReportService = saleForceReportService;
    }

    public List<Liability> getLiabilities() {
        List<SaleforceReport> saleforceReports = saleForceReportService.getAllPartners();
        Map<String, SaleforceReport> saleforceReportsByName = saleforceReports.stream().collect(Collectors.toMap(SaleforceReport::getPartnerName, Function.identity()));
        for (Map.Entry<String,SaleforceReport> entry: saleforceReportsByName.entrySet()){

        }
        return null;
    }
}
