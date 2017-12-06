package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.Liability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return null;
    }
}
