package com.gmail.vx;

import java.lang.reflect.Method;


public class Saver {
    public static void saveInFile() {
        try {
            TextContainer tc = new TextContainer();
            Class<?> cls = TextContainer.class;
            Method[] method = cls.getDeclaredMethods();

            if (cls.isAnnotationPresent(SaveTo.class)) {
                SaveTo st = cls.getAnnotation(SaveTo.class);
                String path = st.path;
            }

            if (method[0].isAnnotationPresent(SaveMethod.class)) {
                if (cls.isAnnotationPresent(SaveTo.class)) {
                    SaveTo st = cls.getAnnotation(SaveTo.class);
                    String path = st.path;
                    method[0].invoke(tc, path);
                }

            }  } catch(Exception e){
                   e.printStackTrace();
            }
    }
}
