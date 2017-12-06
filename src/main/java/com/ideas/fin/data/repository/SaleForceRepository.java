package com.ideas.fin.data.repository;

import com.ideas.fin.data.entity.SaleforceReport;
import org.springframework.data.repository.CrudRepository;

public interface SaleForceRepository extends CrudRepository<SaleforceReport, Long> {
}
