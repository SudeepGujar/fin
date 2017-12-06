package com.ideas.fin.rest;

import com.ideas.fin.data.entity.Liability;
import com.ideas.fin.data.service.CashReceiptService;
import com.ideas.fin.data.service.InvoiceAndCreditsService;
import com.ideas.fin.data.service.MainService;
import com.ideas.fin.data.service.XlsxFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UploadController {

    private final XlsxFileService xlsxFileService;
    private final CashReceiptService cashReceiptService;
    private final InvoiceAndCreditsService invoiceAndCreditsService;
    private final MainService mainService;

    @Autowired
    public UploadController(XlsxFileService xlsxFileService, CashReceiptService cashReceiptService, InvoiceAndCreditsService invoiceAndCreditsService, MainService mainService) {
        this.xlsxFileService = xlsxFileService;
        this.cashReceiptService = cashReceiptService;
        this.invoiceAndCreditsService = invoiceAndCreditsService;
        this.mainService = mainService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public ResponseEntity handleFileUpload(@RequestParam("cashReceipt") MultipartFile cashReceipt, @RequestParam("invoiceAndCredits") MultipartFile invoiceAndCredits) throws Exception {
        final Date uploadDate = new Date();
        cashReceiptService.save(xlsxFileService.read(cashReceipt), cashReceipt.getName(), uploadDate);
        cashReceiptService.save(xlsxFileService.read(invoiceAndCredits), invoiceAndCredits.getName(), uploadDate);
        //invoiceAndCreditsService.save(xlsxFileService.read(invoiceAndCredits), invoiceAndCredits.getName());
        List<Liability> finalReport = mainService.getLiabilities();
        return ResponseEntity.ok().build();
    }
}
