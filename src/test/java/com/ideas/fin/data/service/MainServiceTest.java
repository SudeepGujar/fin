package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.SaleforceReport;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

import static org.junit.Assert.*;

public class MainServiceTest {
    @Mock
    CashReceiptService cashReceiptService;
    @Mock
    SaleForceReportService saleForceReportService;
    @InjectMocks
    MainService mainService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getYearsBetweenDates() throws Exception {
        Date start = new Date(ZonedDateTime.parse("2017-12-01", DATE_TIME_FORMATTER).toInstant().toEpochMilli());
        Date end = new Date(ZonedDateTime.parse("2018-03-30", DATE_TIME_FORMATTER).toInstant().toEpochMilli());
        assertEquals(mainService.getYearsBetweenDates(start,end),1);
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
            .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
            .toFormatter()
            .withZone(ZoneOffset.UTC);
}