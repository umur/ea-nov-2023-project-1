package edu.miu.ea.ap.helper.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    String pattern() default "";
}
