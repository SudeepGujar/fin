package com.ideas.fin.data.service;

import com.ideas.fin.data.entity.Liability;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class XlsxFileService {

    private static final int HEADER_ROW_INDEX = 0;
    public static final List<String> HEADERS = Arrays.asList("Partner","Account", "Hotel", "Business Model", "In which year", "Model percentage", "Invoice#", "Date ", "Client Invoice Amount", "Full Commission Amount ", "Date Paid ", "Amount Received from Client", "Credit to Client", "Client Withholding Tax Amount", "Original Amount Paid to RMC ", "Date Paid to RMC ", "Subsequent Amount Paid to RMC ", "Date Paid to RMC ", "RMC portion of Tax Credit", "Balance Remaining  ", "To A/P?");

    @Autowired
    public XlsxFileService() {
    }

    public List<List<Object>> read(MultipartFile file) throws IOException {

        final XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        final XSSFSheet sheet = workbook.getSheetAt(0);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        final List<List<Object>> result = StreamSupport.stream(sheet.spliterator(), false).map(rows ->
                StreamSupport.stream(rows.spliterator(), false).map(cell -> cell == null ? null : getValueFrom(cell, evaluator)).collect(Collectors.toList())
        ).collect(Collectors.toList());
        result.remove(HEADER_ROW_INDEX);
        return result;
    }

    public void write(List<Liability> result, OutputStream outputStream) {
        final XSSFWorkbook workbook = new XSSFWorkbook();
        final XSSFSheet sheet = workbook.createSheet("Report");
        Row headerRow = sheet.createRow(HEADER_ROW_INDEX);
        for (int i = 0; i < HEADERS.size(); i++) {
            setCellIn(headerRow, HEADERS.get(i), i);
        }
        for (int i = 0; i < result.size(); i++) {
            Liability liability = result.get(i);
            Row row = sheet.createRow(i+1);

            setCellIn(row, liability.partner, 0);
            setCellIn(row, liability.accountNumber, 1);
            setCellIn(row, liability.hotelName, 2);
            setCellIn(row, liability.businessModel, 3);
            setCellIn(row, String.valueOf(liability.year), 4);

            setCellIn(row, String.valueOf(liability.model), 5);
            setCellIn(row, liability.invoiceNumber, 6);
            setCellIn(row, String.valueOf(liability.invoiceDate), 7);
            setCellIn(row, String.valueOf(liability.clientInvoiceAmount), 8);
            setCellIn(row, String.valueOf(liability.fullCommissionAmount), 9);
            setCellIn(row, liability.paidDate != null?String.valueOf(liability.paidDate):"", 10);
            setCellIn(row, liability.paidAmount!=0D?String.valueOf(liability.paidAmount):"", 11);
            setCellIn(row, String.valueOf(liability.originalAmountPaidToPartner), 14);
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("C:/Users/idnsug/Desktop/financeReport/report.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("File created!!");
    }

    private void setCellIn(Row row, String accountNumber, int column) {
        final Cell cell = row.createCell(column);
        cell.setCellValue(accountNumber);
    }

    private Object getValueFrom(Cell cell, FormulaEvaluator evaluator) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();
                }
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case FORMULA:
                return getValueFrom(evaluator.evaluateInCell(cell), null);
            case BLANK:
                return null;
            default:
                return null;
        }
    }
}
