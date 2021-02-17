package com.gmail.vx;

import java.lang.reflect.Method;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        try {
            MyClass mc = new MyClass();
            Class<?> cls = MyClass.class;
            Method[] method = cls.getDeclaredMethods();
            if (method[0].isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation ma = cls.getAnnotation(MyAnnotation.class);
                String p = ma.param;
                Date d = ma.d;
               System.out.println( method[0].invoke(mc, p, d));
            }

        } catch (Exception e) {
           e.printStackTrace();
        }





    }


}
