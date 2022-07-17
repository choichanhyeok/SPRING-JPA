package com.example.filtertest2.annotaion;

import java.lang.annotation.*;



@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Auth {

}
