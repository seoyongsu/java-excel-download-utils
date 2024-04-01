package org.example.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Excel File InterFace
 *
 * @author : Seo yongsu
 */
public interface ExcelFile<T> {

    /**
     * Add Rows
     * @param data : object list
     */
    void addRows(List<T> data);

    void write(OutputStream outputStream) throws IOException;
}
