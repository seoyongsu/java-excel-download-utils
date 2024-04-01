package org.example.style;

import org.example.style.align.ExcelAlign;
import org.example.style.color.ExcelColor;

public class DefaultCellStyle implements ExcelStyle {

    private final ExcelAlign excelAlign;

    private final ExcelColor excelColor;


    public DefaultCellStyle(ExcelAlign excelAlign, ExcelColor excelColor) {
        this.excelAlign = excelAlign;
        this.excelColor = excelColor;
    }

    @Override
    public void apply(org.apache.poi.ss.usermodel.CellStyle cellStyle) {
        excelAlign.apply(cellStyle);
        excelColor.apply(cellStyle);
    }
}
