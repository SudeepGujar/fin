package com.ideas.fin.data.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class XlsxFileService {

    private static final int HEADER_ROW_INDEX = 0;

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

    private Object getValueFrom(Cell cell, FormulaEvaluator evaluator) {
        switch (cell.getCellTypeEnum()) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case NUMERIC:
                if( DateUtil.isCellDateFormatted(cell) ){
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
