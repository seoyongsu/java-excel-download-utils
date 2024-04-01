package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use This Annotation for Excel fields
 * <pre>
 *gu
 * </pre>
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * Colum headerName
     */
    String headerName() default "";

    /**
     * 해더 스타일
     */
    ExcelColumnStyle headerStyle() default @ExcelColumnStyle();

    /**
     * Body 스타일
     */
    ExcelColumnStyle bodyStyle() default @ExcelColumnStyle();
}
