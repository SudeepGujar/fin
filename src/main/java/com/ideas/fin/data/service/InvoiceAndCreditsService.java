package com.ideas.fin.data.service;

import com.ideas.fin.data.repository.InvoiceAndCreditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceAndCreditsService {

    private final InvoiceAndCreditsRepository invoiceAndCreditsRepository;

    @Autowired
    public InvoiceAndCreditsService(InvoiceAndCreditsRepository invoiceAndCreditsRepository) {
        this.invoiceAndCreditsRepository = invoiceAndCreditsRepository;
    }

    public void save(List<List<Object>> invoiceAndCreditsRaw, String filename) {

    }
}
