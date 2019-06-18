package com.housekeeping.validate.common;

import org.apache.commons.lang3.StringUtils;

public class RequireValidate extends AbstractValidate {

    @Override
    protected boolean execute() {
        if (!StringUtils.isBlank(value)) {
            return true;
        } else {
            return false;
        }
    }

}
