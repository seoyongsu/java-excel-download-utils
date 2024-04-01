package org.example.excel;

import org.example.dto.BoardExcelDto;
import org.example.excel.sxssf.SXSSFExcelFile;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;


class ExcelFileTest {

    @Test
    void createExcel() throws IOException {
        String fileName = "demo.xlsx";


        List<BoardExcelDto> data = Arrays.asList(
                new BoardExcelDto("제목_1", "1", "테스트"),
                new BoardExcelDto("제목_2", "222", "테스트"),
                new BoardExcelDto("제목_3", "333", "테스트"),
                new BoardExcelDto("제목_4", "4444", "테스트"),
                new BoardExcelDto("제목_5", "55555", "테스트")
        );


        ExcelFile<BoardExcelDto> excelFile = new SXSSFExcelFile<>(data, BoardExcelDto.class);
        OutputStream outputStream = new FileOutputStream(fileName);
        excelFile.write(outputStream);


    }

}