package CLI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Terminal {
    public static String srcPath="/home/alaa"; // start directory from home

    public static void cd(String argument)
    {
        File File= new File(srcPath);

        if(argument.charAt(0)=='.' && argument.charAt(1)=='/'){// to handel argument like ./Documents
            argument=srcPath+argument.substring(1,argument.length());
        }
        for(int i=0;i< File.listFiles().length;i++)
        {
           // System.out.println(File.listFiles()[i]);
            if(File.listFiles()[i].getName().equals(argument))
                return ;
        }
        File newFile = new File(argument);
        if(!newFile.isDirectory()|| argument.length()==0)  // TODO: chek argument if null
            // without this block if the path not found it creates new directory
        {
            newFile = new File(srcPath);
        }
        srcPath=newFile.getAbsolutePath();
    }

    public static void more(String fileName) {
    File file = new File( srcPath, fileName);
    if (file.exists()) {
        try {
            FileInputStream input = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            String line;
            int numOfLines=0;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if(numOfLines%10==0) System.out.println("........More........");
                numOfLines++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


   /*public static void  more(String path, String name){

       try {
           File file = new File(path,name);
           Scanner fileReader = new Scanner(file);
           while (fileReader.hasNextLine()) {
               String line = fileReader.nextLine();
               System.out.println(line);
           }
           fileReader.close();
       } catch (FileNotFoundException e) {
           System.out.println("error ");
           e.printStackTrace();
       }
   }*/


    public static void  mkdir(String dirName)  {
       try {
           String folderName = srcPath + "/" + dirName;
           Path directoryPath = Paths.get(folderName);

           if (Files.exists(directoryPath)) {
               System.out.println("Directory exists");
           } else {
               Files.createDirectory(directoryPath);
           }
       }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


   public  static void args()
    {
        System.out.println("cd: [arg:destination Path]");
        System.out.println("cd: [no arg] ");
        System.out.println("ls: [no arg]");
        System.out.println("ls: [-a] ");
        System.out.println("cp: [arg1:Src File] [arg2: dest file]");
        System.out.println("mv: cp: [arg1:Src File] [arg2: dest file]");
        System.out.println("mkdir: [arg: folder name or path]");
        System.out.println("rmdir: [arg: folder name or path]");
        System.out.println("rm: [arg: file name]");
        System.out.println("cat: [arg1: file name]");
        System.out.println("cat: [arg1: file 1 name ] [arg2: file 2 name] ");
        System.out.println("help: [no arg]");


        // TODO: complete reminder commands
    }

    public static void main(String args[]) throws IOException {

        File file=new File(srcPath); // starting from home ditrectory
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();
            String commandLine = new String();
            String argument = new String();
            //System.out.println(command);
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == ' ') {
                    commandLine = command.substring(0, i);
                    argument = command.substring(i + 1, command.length());
                }
            }
            // System.out.println(commandLine);
            //System.out.println(argument);
            System.out.println(file.getAbsolutePath());
             cd(argument);
            System.out.println(srcPath);
            //System.out.println(n.getAbsolutePath());
            //more(srcPath,"alaa.txt");
            //mkdir(argument, srcPath);
        }
       /* File myObj = new File(argument,"ilename.txt");
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println(" File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }




