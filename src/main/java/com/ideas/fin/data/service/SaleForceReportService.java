package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.SaleforceReport;
import com.ideas.fin.data.repository.SaleForceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleForceReportService {

    private final SaleForceRepository saleForceRepository;

    @Autowired
    public SaleForceReportService(SaleForceRepository saleForceRepository) {
        this.saleForceRepository = saleForceRepository;
    }

    public List<SaleforceReport> getAllPartners() {
        return (List<SaleforceReport>) saleForceRepository.findAll();
    }
}
