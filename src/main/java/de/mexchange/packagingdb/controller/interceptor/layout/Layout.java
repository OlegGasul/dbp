package de.mexchange.packagingdb.controller.interceptor.layout;

import java.lang.annotation.*;

/**
 * Created by Garik on 5/6/16.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Layout {

	String value() default "layout/default";

	String titleKey() default "title.page.default";
}
