package org.example.style.border;

import org.apache.poi.ss.usermodel.CellStyle;

public class DefaultBorder implements ExcelBorder {

    private final org.apache.poi.ss.usermodel.BorderStyle borderStyle;

    public DefaultBorder(org.apache.poi.ss.usermodel.BorderStyle borderStyle) {
        this.borderStyle = borderStyle;
    }


    @Override
    public void applyTop(CellStyle cellStyle) {
        cellStyle.setBorderTop(this.borderStyle);
    }

    @Override
    public void applyLeft(CellStyle cellStyle) {
        cellStyle.setBorderLeft(this.borderStyle);
    }

    @Override
    public void applyRight(CellStyle cellStyle) {
        cellStyle.setBorderRight(this.borderStyle);
    }

    @Override
    public void applyBottom(CellStyle cellStyle) {
        cellStyle.setBorderBottom(this.borderStyle);
    }
}
