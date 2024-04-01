package org.example.style;


import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 엑셀 컬럼 스타일 interface
 */
public interface ExcelStyle {
    default void apply(CellStyle cellStyle){
        //DO nothing
    }

}
