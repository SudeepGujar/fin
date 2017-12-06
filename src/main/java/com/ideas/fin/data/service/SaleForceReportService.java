package com.ideas.fin.data.service;

import com.ideas.fin.data.repository.SaleForceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleForceReportService {

    private final SaleForceRepository saleForceRepository;

    @Autowired
    public SaleForceReportService(SaleForceRepository saleForceRepository) {
        this.saleForceRepository = saleForceRepository;
    }
}
