package com.ideas.fin.data.entity;

import com.ideas.fin.data.entity.SaleforceReport.BusinessModel;
import org.junit.Test;

public class BusinessModelTest {
    @Test
    public void shouldGetModel(){
        final BusinessModel businessModel = new BusinessModel("Software: 50-20-10 Ongoing; Consulting: 20 One Time");
    }
}