package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.SaleforceReport;
import com.ideas.fin.data.repository.SaleForceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleForceReportService {

    private final SaleForceRepository saleForceRepository;

    @Autowired
    public SaleForceReportService(SaleForceRepository saleForceRepository) {
        this.saleForceRepository = saleForceRepository;
    }

    public void save(List<List<Object>> saleforceReportRaw, String name, Date uploadDate) {
        final List<SaleforceReport> cashReceipts = getCashReceipts(saleforceReportRaw, name, uploadDate);
        saleForceRepository.save(cashReceipts);
    }

    private List<SaleforceReport> getCashReceipts(List<List<Object>> saleforceReportRaw, String name, Date uploadDate) {
        return saleforceReportRaw.stream()
                .map(objects -> new SaleforceReport(objects.get(0).toString(), objects.get(1).toString(), objects.get(2).toString(), (Date) objects.get(3),objects.get(4).toString(), objects.get(5).toString(), (Date)objects.get(6), objects.get(7).toString(), objects.get(8).toString(), objects.get(9).toString(),name, uploadDate))
                .collect(Collectors.toList());
    }

    public List<SaleforceReport> getAllPartners() {
        return (List<SaleforceReport>) saleForceRepository.findAll();
    }
}
