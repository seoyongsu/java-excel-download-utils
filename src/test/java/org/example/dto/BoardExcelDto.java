package org.example.dto;


import org.example.ExcelColumn;
import org.example.ExcelColumnStyle;
import org.example.ExcelResource;
import org.example.style.DefaultCellStyle;

@ExcelResource
public class BoardExcelDto {

    private String title;
    
    @ExcelColumn(headerName = "내용",
            bodyStyle = @ExcelColumnStyle(excelCellStyleClass = DefaultCellStyle.class)
    )
    private String content;

    @ExcelColumn
    private String writer;

    public BoardExcelDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }



    @Override
    public String toString() {
        return "BoardExcelDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }
}
