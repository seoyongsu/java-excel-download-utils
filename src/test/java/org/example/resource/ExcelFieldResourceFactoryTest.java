package org.example.resource;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.example.dto.BoardExcelDto;
import org.example.dto.NoAnnotationDto;
import org.example.exception.NoExcelAnnotationsException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExcelFieldResourceFactoryTest {



    @Test
    void renderingTest(){
        ExcelFieldResource resource = ExcelResourceFactory.rendering(BoardExcelDto.class,  new SXSSFWorkbook(), new DefaultDataFormatDecider());
        
        // Field 목록 확인
        assertThat(resource.getDataFieldNames()).isEqualTo( Arrays.asList("title", "content","writer") );
        //Header 어노테이션 설정값 확인
        assertEquals(resource.getExcelHeaderName("content"), "내용");


        CellStyle headerStyle = resource.getCellStyle("content", ExcelField.Location.HEADER);
        CellStyle bodyStyle = resource.getCellStyle("content", ExcelField.Location.BODY);

    }

    @Test
    void noAnnotationTest(){
        assertThrows(
                NoExcelAnnotationsException.class,()->{
                    ExcelResourceFactory.rendering(NoAnnotationDto.class,  new SXSSFWorkbook(), new DefaultDataFormatDecider());
        });
    }

}