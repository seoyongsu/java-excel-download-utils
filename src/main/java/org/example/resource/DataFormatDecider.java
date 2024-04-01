package org.example.resource;

import org.apache.poi.ss.usermodel.DataFormat;

/**
 * 엑셀 필드의 데이터 Format interface
 * _NONE(-1)
 * NUMERIC(0)
 * STRING(1)
 * FORMULA(2)
 * BLANK(3)
 * BOOLEAN(4)
 * ERROR(5)
 * {@link org.apache.poi.ss.usermodel.CellType}
 */
public interface DataFormatDecider {

    default short getDataFormat(DataFormat dataFormat, Class<?> type){
        return 1;
    }
}
