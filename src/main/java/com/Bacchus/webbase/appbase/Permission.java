package com.Bacchus.webbase.appbase;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.Bacchus.webbase.common.constants.SystemCodeConstants;

@Documented
@Target({ TYPE, METHOD })
@Retention(RUNTIME)
public @interface Permission {
	SystemCodeConstants.Permissions[] value();
}
