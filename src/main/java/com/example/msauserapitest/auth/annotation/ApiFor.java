package com.example.msauserapitest.auth.annotation;

import com.example.msauserapitest.user.enums.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiFor {
    RoleType[] roles() default {RoleType.ALL};
    boolean validateManager() default false;
    boolean validateResident() default false;
}
