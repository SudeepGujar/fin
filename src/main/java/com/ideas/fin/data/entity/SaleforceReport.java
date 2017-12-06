package com.ideas.fin.data.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

public class SaleforceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final long id;

    private final String accountName;
    private final String accountNumbe;
    private final String opportunityName;
    private final String closeDate;
    private final String partnerType;
    private final BusinessModel businessModel;
    private final String accessDate;
    private final String drivingProduct;
    private final String deliveryFrameworkName;
    private final String partnerName;

    public SaleforceReport(long id, String accountName, String accountNumbe, String opportunityName, String closeDate, String partnerType, BusinessModel businessModel, String accessDate, String drivingProduct, String deliveryFrameworkName, String partnerName) {
        this.id = id;
        this.accountName = accountName;
        this.accountNumbe = accountNumbe;
        this.opportunityName = opportunityName;
        this.closeDate = closeDate;
        this.partnerType = partnerType;
        this.businessModel = businessModel;
        this.accessDate = accessDate;
        this.drivingProduct = drivingProduct;
        this.deliveryFrameworkName = deliveryFrameworkName;
        this.partnerName = partnerName;
    }

    public static class BusinessModel {
        private final double firstYear;
        private final double secondYear;
        private final double ongoing;
        private final double consulting;


        public BusinessModel(double firstYear, double secondYear, double ongoing, double consulting) {
            this.firstYear = firstYear;
            this.secondYear = secondYear;
            this.ongoing = ongoing;
            this.consulting = consulting;
        }

        public BusinessModel (String businessModel){
            final List<String> modelSplits = Arrays.asList(businessModel.replaceAll("[^0-9]", " ").trim().replaceAll(" +", " ").split(" "));
            final List<double[]> defaults = Arrays.asList(new double[]{0, 0, 0, 0});

            this.firstYear = Double.parseDouble(modelSplits.get(0));

            this.secondYear = 0;
            this.ongoing = 0;
            this.consulting = 0;
        }
    }
}
