package org.example.style.align;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 정렬
 * {@link org.apache.poi.ss.usermodel.VerticalAlignment}
 * {@link org.apache.poi.ss.usermodel.HorizontalAlignment}
 */
public interface ExcelAlign {

    default void apply(CellStyle cellStyle){
        //DO Nothing
    }
}
