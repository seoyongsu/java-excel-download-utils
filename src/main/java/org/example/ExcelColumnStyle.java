package org.example;

import org.example.style.ExcelStyle;


/**
 * Column Style Annotation
 */
public @interface ExcelColumnStyle {

    Class<? extends ExcelStyle> excelCellStyleClass() default ExcelStyle.class;


}
