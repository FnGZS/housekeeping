package com.housekeeping.validate.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.housekeeping.validate.extend.AbstractExecutor;

public class CustomizeValidate extends AbstractValidate {
    /**
     * 实例化容�??
     */
    private static Map<String, AbstractExecutor> container = new HashMap<String, AbstractExecutor>();

    /**
     * 自定义类
     */
    private Class<AbstractExecutor> customizeClass;

    @Override
    protected boolean execute() {
        if (StringUtils.isBlank(value)) {
            return true;
        }

        AbstractExecutor executor = getInstance(customizeClass);

        return executor.execute(value);
    }

    private AbstractExecutor getInstance(Class<AbstractExecutor> customizeClass) {
        String key = customizeClass.getName();
        AbstractExecutor execute = container.get(key);
        if (execute == null) {
            try {
                execute = customizeClass.newInstance();
                container.put(key, execute);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return execute;
    }

    @SuppressWarnings("rawtypes")
    public Class getCustomizeClass() {
        return customizeClass;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void setCustomizeClass(Class customizeClass) {
        this.customizeClass = customizeClass;
    }

}
