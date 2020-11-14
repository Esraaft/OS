package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here
        Terminal t1=new Terminal();
        Parser p1=new Parser();

        t1.rmdir(new String[]{Path("folder")});


    }

    public static String Path(String input)
    {
        String FullPath;
        FullPath =System.getProperty("user.dir") + "/" +input;
        return FullPath;
    }
}
