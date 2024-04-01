package org.example.resource;

import org.apache.poi.ss.usermodel.Workbook;
import org.example.ExcelColumn;
import org.example.ExcelColumnStyle;
import org.example.ExcelResource;
import org.example.exception.NoExcelAnnotationsException;
import org.example.style.ExcelStyle;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class ExcelResourceFactory {


    public static ExcelFieldResource rendering(Class<?> clazz, Workbook workBook, DataFormatDecider dataFormatDecider){
        ExcelStyleResource excelStyle = new ExcelStyleResource(dataFormatDecider);
        Map<String, String> excelHeaderNames = new LinkedHashMap<>();
        List<String> dataFieldNames = new ArrayList<>();

        ExcelColumnStyle classDefinedHeaderStyle = getHeaderExcelColumnStyle(clazz);
        ExcelColumnStyle classDefinedBodyStyle = getBodyExcelColumnStyle(clazz);

        // @ExcelResource isAnnotationPresent true
        if(clazz.isAnnotationPresent(org.example.ExcelResource.class)){
            for(Field field : clazz.getDeclaredFields()){
                if (field.isAnnotationPresent(ExcelColumn.class)) {
                    ExcelColumn annotation = field.getAnnotation(ExcelColumn.class);
                    //헤더 설정
                    String headerName = annotation.headerName().isEmpty() ? field.getName() : annotation.headerName();
                    dataFieldNames.add(field.getName());
                    excelHeaderNames.put(field.getName(), headerName);

                    //Header Style 설정
                    excelStyle.put(
                            String.class,
                            ExcelField.of(field.getName(), ExcelField.Location.HEADER),
                            getCellStyle( decideAppliedStyleAnnotation(classDefinedHeaderStyle, annotation.headerStyle()) ),
                            workBook
                    );
                    //Body Style 설정
                    excelStyle.put(
                            field.getType(),
                            ExcelField.of(field.getName(), ExcelField.Location.BODY),
                            getCellStyle( decideAppliedStyleAnnotation(classDefinedBodyStyle, annotation.bodyStyle()) ),
                            workBook
                    );

                } else {
                    dataFieldNames.add(field.getName());
                    excelHeaderNames.put(field.getName(), field.getName());
                    excelStyle.put(
                            String.class,
                            ExcelField.of(field.getName(), ExcelField.Location.HEADER),
                            new ExcelStyle() {},
                            workBook
                    );
                    excelStyle.put(
                            field.getType(),
                            ExcelField.of(field.getName(), ExcelField.Location.BODY),
                            new ExcelStyle() {},
                            workBook
                    );
                }
            }

        } else {
            throw new NoExcelAnnotationsException(String.format("Class %s has not @ExcelResource annotation", clazz));
        }
        return new ExcelFieldResource(excelStyle, excelHeaderNames, dataFieldNames);
    }

    private static ExcelColumnStyle getHeaderExcelColumnStyle(Class<?> clazz) {
        ExcelResource.HeaderStyle annotation = clazz.getAnnotation(ExcelResource.HeaderStyle.class);
        if (annotation == null) {
            return null;
        }
        return annotation.style();
    }

    private static ExcelColumnStyle getBodyExcelColumnStyle(Class<?> clazz) {
        ExcelResource.BodyStyle annotation = clazz.getAnnotation(ExcelResource.BodyStyle.class);
        if (annotation == null) {
            return null;
        }
        return annotation.style();
    }

    private static ExcelColumnStyle decideAppliedStyleAnnotation(ExcelColumnStyle classAnnotation,
                                                                 ExcelColumnStyle fieldAnnotation) {

        if (fieldAnnotation.excelCellStyleClass().equals(ExcelStyle.class) && classAnnotation != null) {
            return classAnnotation;
        }
        return fieldAnnotation;
    }

    private static ExcelStyle getCellStyle(ExcelColumnStyle excelColumnStyle){

        try {
            Constructor<? extends ExcelStyle> constructor = excelColumnStyle.excelCellStyleClass().getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            return new ExcelStyle() {};
        }
    }
//    private static ExcelCellStyle getCellStyle(ExcelColumnStyle excelColumnStyle) {
//        ExcelCellStyle excelCellStyleClass = excelColumnStyle.excelCellStyleClass();
//
//        System.out.println("e :" +excelCellStyleClass);
//
//        try {
//            return excelCellStyleClass.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            throw new InvalidExcelCellStyleException(e.getMessage(), e);
//        }
//    }


}
