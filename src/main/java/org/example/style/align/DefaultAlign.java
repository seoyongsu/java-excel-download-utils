package org.example.style.align;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class DefaultAlign implements ExcelAlign {


    @Override
    public void apply(CellStyle cellStyle) {
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
    }
}
