package org.example.resource;

import org.apache.poi.ss.usermodel.CellStyle;

import java.util.*;

/**
 * Cell Colum Header And Fields information
 * @author Yongsu Seo
 * @since 2024-04-01
 */
public class ExcelFieldResource {

    private final ExcelStyleResource excelStyleResource;

    /**
     * Field Name : Header Name
     */
    private final Map<String, String> excelHeaderNames;

    /**
     * Field Names
     */
    private List<String> dataFieldNames;

    public ExcelFieldResource(ExcelStyleResource excelStyleResource, Map<String, String> excelHeaderNames, List<String> dataFieldNames) {
        this.excelStyleResource = excelStyleResource;
        this.excelHeaderNames = excelHeaderNames;
        this.dataFieldNames = dataFieldNames;
    }

    public CellStyle getCellStyle(String dataFieldName, ExcelField.Location excelRenderLocation) {
        return excelStyleResource.get(ExcelField.of(dataFieldName, excelRenderLocation));
    }

    public String getExcelHeaderName(String dataFieldName) {
        return excelHeaderNames.get(dataFieldName);
    }

    public List<String> getDataFieldNames() {
        return dataFieldNames;
    }
}
