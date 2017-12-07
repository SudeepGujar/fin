package com.ideas.fin.data.entity;

import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
public class SaleforceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;

    private final String accountName;
    private final String accountNumber;
    private final String opportunityName;
    private final Date closeDate;
    private final String partnerType;
    private final String businessModel;
    private final Date accessDate;
    private final String drivingProduct;
    private final String deliveryFrameworkName;
    private final String partnerName;
    private final String filename;
    private final Date uploadDate;

    @PersistenceConstructor
    public SaleforceReport(Long id, String accountName, String accountNumber, String opportunityName, Date closeDate, String partnerType, String businessModel, Date accessDate, String drivingProduct, String deliveryFrameworkName, String partnerName, String filename, Date uploadDate) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.opportunityName = opportunityName;
        this.closeDate = closeDate;
        this.partnerType = partnerType;
        this.businessModel = businessModel;
        this.accessDate = accessDate;
        this.drivingProduct = drivingProduct;
        this.deliveryFrameworkName = deliveryFrameworkName;
        this.partnerName = partnerName;
        this.filename = filename;
        this.uploadDate = uploadDate;
    }

    public SaleforceReport(String accountName, String accountNumber, String opportunityName, Date closeDate, String partnerType, String businessModel, Date accessDate, String drivingProduct, String deliveryFrameworkName, String partnerName, String filename, Date uploadDate) {
         this(null,accountName, accountNumber, opportunityName, closeDate, partnerType, businessModel, accessDate, drivingProduct, deliveryFrameworkName, partnerName, filename, uploadDate);
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOpportunityName() {
        return opportunityName;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public BusinessModel getBusinessModel() {
        return new BusinessModel(businessModel);
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public String getDrivingProduct() {
        return drivingProduct;
    }

    public String getDeliveryFrameworkName() {
        return deliveryFrameworkName;
    }

    public String getFilename() {
        return filename;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public String getPartnerName() {
        return partnerName;
    }


    public static class BusinessModel {
        private final double firstYear;
        private final double secondYear;
        private final double ongoing;
        private final double consulting;

        @PersistenceConstructor
        public BusinessModel(double firstYear, double secondYear, double ongoing, double consulting) {
            this.firstYear = firstYear;
            this.secondYear = secondYear;
            this.ongoing = ongoing;
            this.consulting = consulting;
        }

        public BusinessModel(String businessModel) {
            final List<String> modelSplits = Arrays.asList(businessModel.replaceAll("[^0-9]", " ").trim().replaceAll(" +", " ").split(" "));
            final List<Double> model = Arrays.asList(0D, 0D, 0D, 0D);
            for (int i = 0; i < modelSplits.size(); i++) {
                model.set(i,Double.parseDouble(modelSplits.get(i)));
            }

            this.firstYear = model.get(0);
            this.secondYear = model.get(1);
            this.ongoing = model.get(2);
            this.consulting = model.get(3);
        }

        public double getFirstYear() {
            return firstYear;
        }

        public double getSecondYear() {
            return secondYear;
        }

        public double getOngoing() {
            return ongoing;
        }

        public double getConsulting() {
            return consulting;
        }
    }
}
