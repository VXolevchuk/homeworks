package com.gmail.vx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(value = RetentionPolicy.RUNTIME)
public @interface SaveTo {
    String path = "E:\\Програмування\\Гарна папка\\file1.txt";
}
