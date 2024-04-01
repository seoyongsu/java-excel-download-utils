package org.example.resource;


import java.util.Objects;

/**
 * Excel RenderField Object
 */
public class ExcelField {

    private final String dataFieldName;

    private final Location excelRenderLocation;

    public static ExcelField of(String fieldName, Location excelRenderLocation) {
        assert excelRenderLocation != null;
        return new ExcelField(fieldName, excelRenderLocation);
    }

    protected ExcelField(String dataFieldName, Location excelRenderLocation) {
        this.dataFieldName = dataFieldName;
        this.excelRenderLocation = excelRenderLocation;
    }

    public String getDataFieldName() {
        return dataFieldName;
    }

    public Location getExcelRenderLocation() {
        return excelRenderLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelField that = (ExcelField) o;
        return Objects.equals(dataFieldName, that.dataFieldName) && excelRenderLocation == that.excelRenderLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataFieldName, excelRenderLocation);
    }

    /**
     * 엑셀 Field 위치
     */
    public enum Location{
        HEADER,
        BODY,
    }


}
