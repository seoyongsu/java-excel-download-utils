package org.example.resource;

import org.apache.poi.ss.usermodel.DataFormat;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;


public class DefaultDataFormatDecider implements DataFormatDecider {

    private static final Set<Class<?>> WRAPPER_TYPES = Set.of(
            Boolean.class,
            Byte.class,
            Character.class,
            Double.class,
            Float.class,
            Integer.class,
            Long.class,
            Short.class,
            String.class
    );

    /**
     * 기본 구현체에서는 모든 cell Data type은 문자열로 설정
     */
    @Override
    public short getDataFormat(DataFormat dataFormat, Class<?> type) {
        if(type.isPrimitive() || WRAPPER_TYPES.contains(type)){
            return 1;
        }
        return -1;
    }

    /**
     * @param dataFormat
     * @param type
     * @return
     * @throws IllegalAccessException
     */
    private short example2(DataFormat dataFormat, Class<?> type) throws IllegalAccessException {
        Field[] fields = type.getDeclaredFields();;
        for(int i=0; i<fields.length; i++){
            Field field = fields[i];
            field.setAccessible(true);
            Object fieldValue = field.get(field.getName());

            if (fieldValue instanceof Integer || fieldValue instanceof Double) {
                return 0;
            } else if (fieldValue instanceof String) {
               return 1;
            } else if (fieldValue instanceof LocalDateTime) {
                return 1;
            } else if (fieldValue instanceof LocalDate) {
                return 1;
            } else {
               return 1;
            }
        }
        return -1;
    }



}
