package org.example.resource;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.HashMap;
import java.util.Map;

public class ExcelStyleResource {
    private final DataFormatDecider dataFormatDecider;
    private final Map<ExcelField, org.apache.poi.ss.usermodel.CellStyle> cellStyleMap = new HashMap<>();

    public ExcelStyleResource(DataFormatDecider dataFormatDecider) {
        this.dataFormatDecider = dataFormatDecider;
    }

    public void put(Class<?> fieldType, ExcelField excelField, org.example.style.ExcelStyle excelStyle, Workbook wb) {
        org.apache.poi.ss.usermodel.CellStyle cellStyle = wb.createCellStyle();
        DataFormat dataFormat = wb.createDataFormat();
        cellStyle.setDataFormat(dataFormatDecider.getDataFormat(dataFormat, fieldType));
        excelStyle.apply(cellStyle);
        cellStyleMap.put(excelField, cellStyle);
    }

    public CellStyle get(ExcelField excelCellKey) {
        return cellStyleMap.get(excelCellKey);
    }

    public boolean isEmpty() {
        return cellStyleMap.isEmpty();
    }
}
