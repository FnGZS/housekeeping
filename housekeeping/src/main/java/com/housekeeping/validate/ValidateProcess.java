package com.housekeeping.validate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.housekeeping.utils.MobileUtils;
import com.housekeeping.validate.annotation.LengthAnno;
import com.housekeeping.validate.annotation.NumberAnno;
import com.housekeeping.validate.annotation.RegexAnno;
import com.housekeeping.validate.annotation.RequireAnno;
import com.housekeeping.validate.common.AbstractValidate;
import com.housekeeping.validate.common.ValidateResult;

import net.sf.cglib.beans.BeanMap;

/**
 * @Type ValidateProcess
 * @Desc 注释验证
 */
public class ValidateProcess {

    /**
     * 是否有注释验证信�??
     * 
     * @param paramType
     * @return
     */
    public static boolean hasAnnotation(Class<?> cla) {
        for (Class<?> curCla = cla; curCla != null; curCla = curCla.getSuperclass()) {
            if (curCla == Object.class) {
                break;
            }
            Field[] fields = curCla.getDeclaredFields();
            for (Field field : fields) {
                Annotation[] annotations = field.getAnnotations();
                for (Annotation annotation : annotations) {
                    Class<?> type = annotation.annotationType();
                    if (type.equals(RequireAnno.class) || type.equals(NumberAnno.class) || type.equals(RegexAnno.class)
                            || type.equals(LengthAnno.class)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 通过对象注解验证数据
     * 
     * @param entity
     * @return
     */
    private static Map<String, String> validate(Object entity) {
        Map<String, String> errorMap = new HashMap<String, String>();
        if (entity == null) {
            return errorMap;
        }
        // 获得对象,及继承对�??
        recursionFieldValidate(entity, errorMap);
        return errorMap;
    }

    /**
     * 验证对象
     * 
     * @param entity
     * @return
     */
    public static ValidateResult validateObject(Object entity) {
        ValidateResult result = new ValidateResult();
        Map<String, String> errorsMap = validate(entity);
        if (errorsMap.size() == 0) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
            StringBuffer errors = new StringBuffer();
            for (String key : errorsMap.keySet()) {
                if (errors.length() != 0) {
                    errors.append(",");
                }
                String value = errorsMap.get(key);
                String defaultError = ValidateFactory.getDefaultError(value);
                if (defaultError != null) {
                    errors.append(key).append(":").append(defaultError);
                } else {
                    errors.append(value);
                }
            }
            result.setErrorMsg(errors.toString());
        }
        return result;
    }

    /**
     * 递归验证对象属�?�及属�?�为对象的�??
     * 
     * @param entity
     * @param errorMap
     */
    private static void recursionFieldValidate(Object entity, Map<String, String> errorMap) {
        BeanMap fieldMap = BeanMap.create(entity);

        // 循环父类
        for (Class<?> cla = entity.getClass(); cla != null; cla = cla.getSuperclass()) {
            if (cla == Object.class) {
                break;
            }
            Field[] fields = cla.getDeclaredFields();
            for (Field field : fields) {
                String fieldName = field.getName();
                Object object = fieldMap.get(fieldName);
                // 当时List容器�??
                if (object != null && field.getType().equals(List.class)) {
                    for (Object itemObject : (List<?>) object) {
                        recursionFieldValidate(itemObject, errorMap);
                    }
                }
                // 判断是个类对�??
                else if (object != null && !MobileUtils.isBaseObject(object)) {
                    recursionFieldValidate(object, errorMap);
                }
                // 判断属�?�是基础对象
                else {
                    validateField(field, fieldMap, errorMap);
                }
            }

        }

    }

    /**
     * 验证字段数据
     * 
     * @param field
     * @param fieldMap
     * @param errorMap
     */
    private static void validateField(Field field, BeanMap fieldMap, Map<String, String> errorMap) {
        String value;
        String fieldName = field.getName();
        Object object = fieldMap.get(fieldName);
        if (object == null) {
            value = "";
        } else if (MobileUtils.isBaseObject(object)) {
            value = object.toString();
        } else {
            return;
        }

        Annotation[] annotations = field.getAnnotations();
        for (Annotation annotation : annotations) {
            Class<?> type = annotation.annotationType();
            AbstractValidate validate = ValidateFactory.getValidate(field, type, value);
            if (validate == null) {
                continue;
            }
            ValidateResult result = validate.validate();
            if (!result.isSuccess()) {
                // �??个属性的错误合并到一�??
                if (errorMap.containsKey(fieldName)) {
                    String data = errorMap.get(fieldName);
                    errorMap.put(fieldName, data + "," + result.getErrorMsg());
                } else {
                    errorMap.put(fieldName, result.getErrorMsg());
                }
            }

        }
    }

}
