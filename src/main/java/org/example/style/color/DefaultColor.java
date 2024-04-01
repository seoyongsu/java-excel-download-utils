package org.example.style.color;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

/**
 * RGB로 기본 컬러 구성
 */
public class DefaultColor implements ExcelColor {

    private static final int MIN_RGB = 0;
    private static final int MAX_RGB = 255;

    private byte red;
    private byte green;
    
    private byte blue;

    private DefaultColor(byte red, byte green, byte blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public static DefaultColor rgb(int red, int green, int blue) {
        if (red < MIN_RGB || red > MAX_RGB || green < MIN_RGB ||
                green > MAX_RGB || blue < MIN_RGB || blue > MAX_RGB) {
            throw new IllegalArgumentException(
                    String.format("Wrong RGB(%s %s %s)", red, green, blue)
            );
        }
        return new DefaultColor((byte) red, (byte) green, (byte) blue);
    }
    @Override
    public void apply(CellStyle cellStyle) {
        XSSFCellStyle xssfCellStyle = (XSSFCellStyle) cellStyle;
        xssfCellStyle.setFillForegroundColor(new XSSFColor(new byte[]{red, green, blue}, new DefaultIndexedColorMap()));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    }
}
