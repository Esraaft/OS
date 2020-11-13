package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;


// Interface for com.company.Terminal
public class Terminal{

    public void ls(String sourcePath) throws IOException {
        //i redeclare the string of sourcepath as a path to use it in DirectoryStream;
        Path path = Paths.get(sourcePath);
        //declare object of type DirectoryStream to iterate ove directories
        DirectoryStream<Path> data;
        data = Files.newDirectoryStream(path);
        for (Path file : data)
        {
            //printing name of file
            System.out.print(file.getFileName()+"  ");
        }
        System.out.println();
    }

    public void rmdir (String sourcePath)
    {
        File path = new File(sourcePath);
        //delete folder if exist and empty
        if(!path.delete()){
            System.out.println("invalid directory");}
    }

    public void date ()
    {
        //printing date
        Date date = new Date();
        System.out.println(date.toString());
    }

    public void clear() {
        //clear terminal
        System.out.print("\033[H\033[2J");
    }

    public void pipe(String[] command)
    {

    }

    //Print Working Directory
    public static void pwd() {
        //the current direction
        String PWD = System.getProperty("user.dir");
        System.out.println(PWD);
    }
    //Remove file
    public static void rm(String fileYouWantToDel){
        //create object from class file store file you to delete
        File file = new File(fileYouWantToDel);
        //check if Directory
        if(file.isDirectory()){
            System.out.println("rm: cannot remove "+file+": Is a directory");
        }
        else{
            System.out.println(file.isFile());
            if(file.isFile() ){file.delete();}
            else { System.out.println("rm: cannot remove "+file+": No such file or directory"); }

        }

    }
    //Read and print text files
    public static void readPrintTextFiles(String textFile) throws FileNotFoundException {
        File file = new File(textFile);
        if(file.isDirectory()){
            System.out.println("cat: "+ file+" : Is a directory");
        }
        else{
            if(file.isFile()){
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine())
                    System.out.println(sc.nextLine());
            }
            else {
                System.out.println("cat: "+ file+" : No such file or directory");
            }
        }
    }
    //concatenate files
    public static void cat(String[] fileYouWantToCat) throws FileNotFoundException {
        for (int i=0;i<fileYouWantToCat.length;i++) {
            readPrintTextFiles(fileYouWantToCat[i]);
        }
    }
    /*public  static  void RedirectOperator(String OutPut,String cmd,String[] string) throws IOException {
        File myObj = new File(OutPut);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
            FileWriter myWriter = new FileWriter(OutPut);
            myWriter.append(cmd);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } else {

            System.out.println("File already exists.");
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(OutPut, true));
            out.write(cmd);
            out.close();
            System.out.println("Successfully wrote to the file.");
        }



    }*/


// Add any other required command in the same structure….. 
}