package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Use this Annotation for ExcelResource Object Class
 * <pre>
 *      A class using this annotation will create a Cell with all fields.
 * </pre>
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelResource {

    // Super class도 같이 읽을 건지 여부
    // TODO ...
    boolean callSuper() default false;

    // 엑셀 Resource 이름 = 파일명 PREFIX
    String name() default "";



    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface HeaderStyle{
        ExcelColumnStyle style();
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    @interface BodyStyle{
        ExcelColumnStyle style();
    }
}
