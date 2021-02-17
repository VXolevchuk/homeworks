package com.gmail.vx;

import java.util.Date;

public class MyClass {
    public MyClass() {

    }

    @MyAnnotation
    public static String concat(String c, Date d){
        String fin;
        String st = "Hello ";
        fin = st + c + " "+d;
        return fin;

    }
}
