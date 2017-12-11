package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.SaleforceReport;
import com.ideas.fin.data.repository.SaleForceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
                .map(objects -> new SaleforceReport(String.valueOf(objects.get(0)), String.valueOf(objects.get(1)), String.valueOf(objects.get(2)), getDate(objects.get(3)), String.valueOf(objects.get(4)), String.valueOf(objects.get(5)), getDate(objects.get(6)), String.valueOf(objects.get(7)), String.valueOf(objects.get(8)), String.valueOf(objects.get(9)), name, uploadDate))
                .collect(Collectors.toList());
    }

    private Date getDate(Object o) {
        if (o == null) {
            return null;
        }
        if (o.getClass().equals(Date.class)) {
            return (Date) o;
        }
        if (o.getClass().equals(String.class)) {
            final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            return Date.from(LocalDate.parse(o.toString(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC));
        }
        return null;
    }

    public List<SaleforceReport> getAllPartners() {
        return StreamSupport.stream(saleForceRepository.findAll().spliterator(), false)
                .collect(Collectors.groupingBy(SaleforceReport::getAccountNumber))
                .entrySet().stream().map(entry -> entry.getValue().get(0)).collect(Collectors.toList());
    }
}
