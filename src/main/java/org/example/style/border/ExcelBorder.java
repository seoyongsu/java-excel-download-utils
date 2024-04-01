package org.example.style.border;

import org.apache.poi.ss.usermodel.CellStyle;

/**
 * 엑셀 컬럼 테두리 스타일 interface
 * {@link org.apache.poi.ss.usermodel.BorderStyle}
 * 0 : NONE: 테두리가 없음
 * 1 : THIN: 얇은 선
 * 2 : MEDIUM: 중간 두께 선
 * 3 : DASHED: 대시(dash) 선
 * 4 : DOTTED: 점선
 * 5 : THICK: 두꺼운 선
 * 6 : DOUBLE: 이중 선
 * 7 : HAIR: 매우 얇은 선
 * 8 : MEDIUM_DASHED: 중간 두께 대시 선
 * 9 : DASH_DOT: 대시-점 선
 * 10 : MEDIUM_DASH_DOT: 중간 두께 대시-점 선
 * 11 : DASH_DOT_DOT: 대시-점-점 선
 * 12 : MEDIUM_DASH_DOT_DOT: 중간 두께 대시-점-점 선
 * 13 : SLANTED_DASH_DOT: 기울어진 대시-점 선
 *
 * @author Seoyongsu
 */
public interface ExcelBorder {

    /**
     * 상단에 테투리 스타일 적용
     */
    void applyTop(CellStyle cellStyle);

    /**
     * 좌측에 테투리 스타일 적용
     */
    void applyLeft(CellStyle cellStyle);

    /**
     * 우측에 테투리 스타일 적용
     */
    void applyRight(CellStyle cellStyle);

    /**
     * 하단에 테두리 스타일 적용
     */
    void applyBottom(CellStyle cellStyle);
}
