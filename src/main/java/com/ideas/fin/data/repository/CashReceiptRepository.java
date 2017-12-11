package com.ideas.fin.data.repository;

import com.ideas.fin.data.entity.CashReceipts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CashReceiptRepository extends CrudRepository<CashReceipts, Long>{
    List<CashReceipts> findByCustomerNumberAndCustomerNameAndDocumentType(String customerNumber, String customerName, String documentType);
}

