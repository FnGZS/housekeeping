package com.housekeeping.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.beans.BeanMap;

public class PropertiesUtils {
	
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    // 读取资源文件,并处理中文乱码
    public static Properties readPropertiesFile(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            properties.load(inputStream);
            inputStream.close(); // 关闭流
        } catch (IOException e) {
            properties = null;
            logger.info(e.getMessage());
        }
        return properties;
    }

    // 读取XML文件,并处理中文乱码
    public static Properties readPropertiesFileFromXML(String filename) {
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(filename);
            properties.loadFromXML(inputStream);
            inputStream.close();
        } catch (IOException e) {
            properties = null;
            logger.info(e.getMessage());
        }

        return properties;
    }

    /**
     * 递归设置null字段为""
     * 
     * @param entity
     */
    public static void setNullProperties(Object entity) {
        if (entity == null) {
            return;
        }
        BeanMap fieldMap = BeanMap.create(entity);
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Object properties = fieldMap.get(fieldName);
            if (MobileUtils.isBaseObject(field.getType())) {
                if (field.getType().equals(String.class)) {
                    if (properties == null) {
                        fieldMap.put(fieldName, "");
                    }
                } else if (field.getType().equals(Integer.class)) {
                    if (properties == null) {
                        fieldMap.put(fieldName, 0);
                    }
                } else if (field.getType().equals(Long.class)) {
                    if (properties == null) {
                        fieldMap.put(fieldName, 0L);
                    }
                } else if (field.getType().equals(Float.class)) {
                    if (properties == null) {
                        fieldMap.put(fieldName, 0.0F);
                    }
                } else if (field.getType().equals(Double.class)) {
                    if (properties == null) {
                        fieldMap.put(fieldName, 0.0);
                    }
                }
            }
            // 当时List容器时
            else if (field.getType().equals(List.class)) {
                if (properties != null) {
                    for (Object obj : (List<?>) properties) {
                        setNullProperties(obj);
                    }
                } else {
                    fieldMap.put(fieldName, new ArrayList<Object>());
                }
            } else if (properties != null) {
                setNullProperties(properties);
            }
        }

    }
}
