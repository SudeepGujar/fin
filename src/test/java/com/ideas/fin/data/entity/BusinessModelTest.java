package com.ideas.fin.data.entity;

import com.ideas.fin.data.entity.SaleforceReport.BusinessModel;
import org.junit.Assert;
import org.junit.Test;

public class BusinessModelTest {
    @Test
    public void shouldGetModel(){
        final BusinessModel businessModel = new BusinessModel("Software: 50-20-10 Ongoing; Consulting: 20 One Time");
        Assert.assertEquals(50, businessModel.getFirstYear(), 0);
        Assert.assertEquals(20, businessModel.getSecondYear(), 0);
        Assert.assertEquals(10, businessModel.getOngoing(), 0);
        Assert.assertEquals(20, businessModel.getConsulting(), 0);
    }

    @Test
    public void shouldGetModelOneTime(){
        final BusinessModel businessModel = new BusinessModel("10 One Time");
        Assert.assertEquals(10, businessModel.getFirstYear(), 0);
        Assert.assertEquals(0, businessModel.getSecondYear(), 0);
        Assert.assertEquals(0, businessModel.getOngoing(), 0);
        Assert.assertEquals(0, businessModel.getConsulting(), 0);
    }

    @Test
    public void shouldGetModelOngoing(){
        final BusinessModel businessModel = new BusinessModel("50-40-30");
        Assert.assertEquals(50, businessModel.getFirstYear(), 0);
        Assert.assertEquals(40, businessModel.getSecondYear(), 0);
        Assert.assertEquals(30, businessModel.getOngoing(), 0);
        Assert.assertEquals(0, businessModel.getConsulting(), 0);
    }

    @Test
    public void shouldGetEmptyModel(){
        final BusinessModel businessModel = new BusinessModel("null");
        Assert.assertEquals(0, businessModel.getFirstYear(), 0);
        Assert.assertEquals(0, businessModel.getSecondYear(), 0);
        Assert.assertEquals(0, businessModel.getOngoing(), 0);
        Assert.assertEquals(0, businessModel.getConsulting(), 0);
    }
}