package com.gmail.vx;

import java.io.*;

@SaveTo
public class TextContainer {
    public  String s = "Hi everybody!";

    @SaveMethod
    public  void save(String p)  {
        try (PrintWriter a = new PrintWriter(p)) {
            a.write(s);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR FILE WRITE");
        }


    }

}
