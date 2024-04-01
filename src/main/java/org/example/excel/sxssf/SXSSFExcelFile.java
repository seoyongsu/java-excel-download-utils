package org.example.excel.sxssf;

import org.apache.poi.ss.SpreadsheetVersion;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.example.excel.ExcelFile;
import org.example.exception.ExcelInternalException;
import org.example.resource.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * It is implemented as the SXSSF
 * <pre>
 *     this class ExcelFile Large capacity implements base
 * </pre>
 * @author Seo yongsu
 * @since 2024-04-01
 * @param <T>
 */
public class SXSSFExcelFile<T> implements ExcelFile<T> {

    protected final static SpreadsheetVersion supplyExcelVersion = SpreadsheetVersion.EXCEL2007;
    protected final static int ROW_ACCESS_WINDOW_SIZE = 1000;
    protected SXSSFWorkbook workbook;
    protected SXSSFSheet sheet;
    protected ExcelFieldResource excelFieldResource;

    /**
     * 생성자 함수
     */
    public SXSSFExcelFile(Class<T> clazz){
        this(Collections.emptyList(), clazz, new DataFormatDecider() {});
    }

    public SXSSFExcelFile(List<T> data, Class<T> clazz){
        this(data, clazz, new DataFormatDecider() {});
    }
    public SXSSFExcelFile(List<T> data, Class<T> clazz, DataFormatDecider dataFormatDecider){
        verify(data);
        this.workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
        // Excel 파일 만들때 tamp 파일 생성 여부
        this.workbook.setCompressTempFiles(true);
        this.excelFieldResource = ExcelResourceFactory.rendering(clazz, workbook, dataFormatDecider);

        this.sheet = this.workbook.createSheet();
        this.writeHeader(this.sheet, clazz);

        for(T body : data){
            this.writeBody(body);;
        }
    }

    protected void verify(List<T> data) {
        int maxRows = supplyExcelVersion.getMaxRows();
        if (data.size() > maxRows) {
            throw new IllegalArgumentException(
                    String.format("This concrete ExcelFile does not support over %s rows", maxRows));
        }
    }


    /**
     * Write header
     */
    protected void writeHeader(Sheet sheet, Class<T> clazz) {
        List<String> filedNames = excelFieldResource.getDataFieldNames();
        Row row = sheet.createRow(0);
        for (int i=0; i<filedNames.size(); i++){
            String fieldName = filedNames.get(i);
            String headerName = excelFieldResource.getExcelHeaderName(fieldName);
            Cell cell = row.createCell(i);
            cell.setCellStyle(excelFieldResource.getCellStyle(fieldName, ExcelField.Location.HEADER));
            cell.setCellValue(headerName);
        }
    }

    /**
     * Write Body
     */
    protected void writeBody(T data) {
        Row row = sheet.createRow(sheet.getLastRowNum()+1);
        List<String> fieldNames = excelFieldResource.getDataFieldNames();

        for(int i=0; i < fieldNames.size(); i++){
            Cell cell = row.createCell(i);
            String fieldName = fieldNames.get(i);
            try {
                Field field = data.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                cell.setCellStyle(excelFieldResource.getCellStyle(fieldName, ExcelField.Location.BODY));
                Object cellValue = field.get(data);
                cell.setCellValue(cellValue == null ? "" : cellValue.toString());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new ExcelInternalException(e.getMessage(), e);
            }
        }
    }




    @Override
    public void addRows(List<T> data) {
        for (T body : data){
            writeBody(body);
        }
    }

    @Override
    public void write(OutputStream outputStream) throws IOException {
        this.workbook.write(outputStream);
        this.workbook.close();
        this.workbook.dispose();
        outputStream.close();
    }
}
