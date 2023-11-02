package ru.inno.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Authorized {
    String username() default "";
    String password() default "";
}