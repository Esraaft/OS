import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Scanner;



public class Terminal {

    public static String srcPath="D:\\"; // start directory from here
	static boolean oneRedirect=false,twoRedirect=false;
	static File fileToRedirectTo=null;
	 public static void WrtieToFile(String text,File file)throws IOException {
		 if(oneRedirect) {
		      file.createNewFile();

		      FileOutputStream outputStream = new FileOutputStream(file);
		      byte[] strToBytes = text.getBytes();
		      outputStream.write(strToBytes);
		      outputStream.close(); 
		      Terminal.oneRedirect=false;
		 }
		 else if(twoRedirect) {
			 OutputStream os = new FileOutputStream(file, true);
			 os.write(text.getBytes(), 0, text.length());
			 os.close();
			 Terminal.twoRedirect=false;
		 }
                 
		 }
    public static void ls() throws IOException {
        //i redeclare the string of sourcepath as a path to use it in DirectoryStream;
        String sourcePath=Terminal.srcPath;
        Path path = Paths.get(sourcePath);
        //declare object of type DirectoryStream to iterate ove directories
        DirectoryStream<Path> data;
        data = Files.newDirectoryStream(path);
        String fullText="";
        if(oneRedirect||twoRedirect) {
             for (Path file : data)
             fullText+=file.getFileName().toString()+" ";

             Terminal.WrtieToFile(fullText,Terminal.fileToRedirectTo );
        }
       
        else {
        for (Path file : data)
        {
            //printing name of file
            System.out.print(file.getFileName()+"  ");

           
        }

        System.out.println();
        }
    }

    public static void rmdir (String sourcePath)
    {
        File path = new File(sourcePath);
        //delete folder if exist and empty
        if(!path.delete()){
            System.out.println("invalid directory");}
    }

    public static void date () throws IOException
    {
    	 //printing date
        Date date = new Date();
    	if(oneRedirect||twoRedirect) {

        	Terminal.WrtieToFile(date.toString(),Terminal.fileToRedirectTo );
       }
    
       else {
    	
        System.out.println(date.toString());
       }
    	}

    public static void clear() throws IOException, InterruptedException  {
        String a = "\n";
        for(int i=0;i<50;i++){
        out.println(a);}
    
    }
    //Print Working Directory
    public static void pwd() throws IOException {
        //the current direction
    	if(oneRedirect||twoRedirect) {

        	Terminal.WrtieToFile(Terminal.srcPath,Terminal.fileToRedirectTo );
       }
       
       else {

        System.out.println(Terminal.srcPath);
       }    }
    //Remove file
    public static void rm(String fileYouWantToDel){
        //create object from class file store file you to delete
        File file = new File(fileYouWantToDel);
        //check if Directory
        if(file.isDirectory()){
            // System.out.println("rm: cannot remove "+file+": Is a directory");
            Terminal.deleteFolder(file);
        }
        else{
            if(file.isFile() ){file.delete();}
            else { System.out.println("rm: cannot remove "+file+": No such file or directory"); }
        }
    }
    static void deleteFolder(File file){
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
    //Read and print text files
    public static void readPrintTextFiles(String textFile) throws FileNotFoundException, IOException {
        File file = new File(Terminal.srcPath,textFile);
        if(file.isDirectory()){
            System.out.println("cat: "+ file+" : Is a directory");
        }
        else{
            if(file.isFile()){
                Scanner sc = new Scanner(file);
                String fullText="";
                if(oneRedirect||twoRedirect) {
                	while (sc.hasNextLine())
                		fullText+=sc.nextLine();
                	Terminal.WrtieToFile(fullText,Terminal.fileToRedirectTo );
                }
                else {
                	while (sc.hasNextLine())
                    System.out.println(sc.nextLine());}
            }
            else {
                System.out.println("cat: "+ file+" : No such file or directory");
            }
        }
    }
    //concatenate files
    public static void cat(String[] fileYouWantToCat) throws FileNotFoundException ,IOException{
        for (int i=0;i<fileYouWantToCat.length;i++) {
            readPrintTextFiles(fileYouWantToCat[i]);
        }

    }

    public static void cp(String sourcePath, String destinationPath ) throws FileNotFoundException, IOException{
        File dest=new File(destinationPath);
        destinationPath+="\\";
        if(dest.isDirectory()) {
            String temp="";
            for(int i=sourcePath.length()-1;i>0;i--) {
                if(sourcePath.charAt(i)=='\\') {
                    break;}
                else {
                    temp+=sourcePath.charAt(i);
                }
            }
            for(int i=temp.length()-1;i>=0;i--) {
                destinationPath+=temp.charAt(i);
            }
        }
        InputStream in = null;
        OutputStream out = null;

        try
        {
            // If file, then copy it
            in = new FileInputStream(sourcePath);
            out = new FileOutputStream(destinationPath);

            byte[] buffer = new byte[1024];

            int length;
            // Copy the file content in bytes
            while ((length = in.read(buffer)) > 0)
            {
                out.write(buffer, 0, length);
            }

        }
        finally
        {
            if (in != null)
            {
                in.close();
            }
            if (out != null)
            {
                out.close();
            }
        }
        // System.out.println("File copied from " + sourcePath + " to " + destinationPath);
    }
    public static void mv(String sourcePath, String destinationPath)throws IOException {
        //we will move the file by copying it to the destination and then deleting the original file
        Terminal.cp(sourcePath, destinationPath);
        File src=new File(sourcePath);
        src.delete();
    }
    public static void help() throws IOException {
    	String help="GNU bash, version 5.0.17(1)-release (x86_64-pc-linux-gnu)\r\n" +
                "These shell commands are defined internally.  Type `help' to see this list.\r\n" +
                "Type `help name' to find out more about the function `name'.\r\n" +
                "Use `info bash' to find out more about the shell in general.\r\n" +
                "Use `man -k' or `info' to find out more about commands not in this list.\r\n" +
                "\r\n" +
                "A star (*) next to a name means that the command is disabled.\r\n" +
                "\r\n" +
                " job_spec [&]                            history [-c] [-d offset] [n] or hist>\r\n" +
                " (( expression ))                        if COMMANDS; then COMMANDS; [ elif C>\r\n" +
                " . filename [arguments]                  jobs [-lnprs] [jobspec ...] or jobs >\r\n" +
                " :                                       kill [-s sigspec | -n signum | -sigs>\r\n" +
                " [ arg... ]                              let arg [arg ...]\r\n" +
                " [[ expression ]]                        local [option] name[=value] ...\r\n" +
                " alias [-p] [name[=value] ... ]          logout [n]\r\n" +
                " bg [job_spec ...]                       mapfile [-d delim] [-n count] [-O or>\r\n" +
                " bind [-lpsvPSVX] [-m keymap] [-f file>  popd [-n] [+N | -N]\r\n" +
                " break [n]                               printf [-v var] format [arguments]\r\n" +
                " builtin [shell-builtin [arg ...]]       pushd [-n] [+N | -N | dir]\r\n" +
                " caller [expr]                           pwd [-LP]\r\n" +
                " case WORD in [PATTERN [| PATTERN]...)>  read [-ers] [-a array] [-d delim] [->\r\n" +
                " cd [-L|[-P [-e]] [-@]] [dir]            readarray [-d delim] [-n count] [-O >\r\n" +
                " command [-pVv] command [arg ...]        readonly [-aAf] [name[=value] ...] o>\r\n" +
                " compgen [-abcdefgjksuv] [-o option] [>  return [n]\r\n" +
                " complete [-abcdefgjksuv] [-pr] [-DEI]>  select NAME [in WORDS ... ;] do COMM>\r\n" +
                " compopt [-o|+o option] [-DEI] [name .>  set [-abefhkmnptuvxBCHP] [-o option->\r\n" +
                " continue [n]                            shift [n]\r\n" +
                " coproc [NAME] command [redirections]    shopt [-pqsu] [-o] [optname ...]\r\n" +
                " declare [-aAfFgilnrtux] [-p] [name[=v>  source filename [arguments]\r\n" +
                " dirs [-clpv] [+N] [-N]                  suspend [-f]\r\n" +
                " disown [-h] [-ar] [jobspec ... | pid >  test [expr]\r\n" +
                " echo [-neE] [arg ...]                   time [-p] pipeline\r\n" +
                " enable [-a] [-dnps] [-f filename] [na>  times\r\n" +
                " eval [arg ...]                          trap [-lp] [[arg] signal_spec ...]\r\n" +
                " exec [-cl] [-a name] [command [argume>  true\r\n" +
                " exit [n]                                type [-afptP] name [name ...]\r\n" +
                " export [-fn] [name[=value] ...] or ex>  typeset [-aAfFgilnrtux] [-p] name[=v>\r\n" +
                " false                                   ulimit [-SHabcdefiklmnpqrstuvxPT] [l>\r\n" +
                " fc [-e ename] [-lnr] [first] [last] o>  umask [-p] [-S] [mode]\r\n" +
                " fg [job_spec]                           unalias [-a] name [name ...]\r\n" +
                " for NAME [in WORDS ... ] ; do COMMAND>  unset [-f] [-v] [-n] [name ...]\r\n" +
                " for (( exp1; exp2; exp3 )); do COMMAN>  until COMMANDS; do COMMANDS; done\r\n" +
                " function name { COMMANDS ; } or name >  variables - Names and meanings of so>\r\n" +
                " getopts optstring name [arg]            wait [-fn] [id ...]\r\n" +
                " hash [-lr] [-p pathname] [-dt] [name >  while COMMANDS; do COMMANDS; done\r\n" +
                " help [-dms] [pattern ...]               { COMMANDS ; }";
    	if(oneRedirect||twoRedirect) {

        	Terminal.WrtieToFile(help,Terminal.fileToRedirectTo );
    	}
        System.out.println(help);
    }


    public static void cd(String argument) {

        if (argument == null) {     // if there is no args
            argument = srcPath;
        }

        File f1=new File (argument);

        if(f1.isDirectory()){
         srcPath=f1.getAbsolutePath();
         }

        else{
          File File = new File(srcPath);
             if (argument.charAt(0) == '.' && argument.charAt(1) == '/') {// to handel argument like ./Documents
                argument = srcPath + argument.substring(1, argument.length());
            }
            else {
                argument = srcPath + argument;
            }

            for (int i = 0; i < File.listFiles().length; i++) {
                // System.out.println(File.listFiles()[i]);
                if (File.listFiles()[i].getName().equals(argument))
                    return;
            }

            File newFile = new File(argument);
            if (!newFile.isDirectory())
            // without this block if the path not found it creates new directory
            {
                newFile = new File(srcPath);
            }
            srcPath = newFile.getAbsolutePath();
        }

    }
    


public static void  printByClickEnter(){

        try{
            System.in.read();
        } catch(Exception e){}
    }


    public static void more(String fileName) {
        if(oneRedirect||twoRedirect) {
            String fullText="";
            File file = new File( srcPath, fileName);
            if (file.exists()) {
                try {
                    String line;
                    int numOfLines=0;

                    Scanner fileReader = new Scanner(file);
                    while (fileReader.hasNextLine()) {
                        line = fileReader.nextLine();
                        fullText+=line+"\n";

                        Terminal.WrtieToFile(fullText,Terminal.fileToRedirectTo );
                    }
                    fileReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                System.out.println("No such file with name "+fileName);
            }
        }


        else {
            File file = new File( srcPath, fileName);
            if (file.exists()) {
                try {
                    String line;
                    int numOfLines=0;
                    Scanner fileReader = new Scanner(file);
                    while (fileReader.hasNextLine()) {
                        line = fileReader.nextLine();
                        System.out.println(line);
                        if(numOfLines==20 ||numOfLines>20) {
                            printByClickEnter();
                        }
                        numOfLines++;
                    }
                    fileReader.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            else {
                System.out.println("No such file with name "+fileName);
            }
        }
    }


    public static void  mkdir(String dirName)  {
        try {
            String folderName = srcPath + dirName;
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


public  static void args(String argument) throws IOException
    {
        switch(argument) {

            case "pwd":
            case "help":
            case "clear":
            case "ls":
            case "date": System.out.println(" [no args]");
                break;
            case "cat": System.out.println(" [arg1: File1 name] [arg2:file2 name]");
                break;
            case "cp":
            case "rm": System.out.println("[arg1: srcPath] [arg2:destPath]");
                break;
            case "cd":  System.out.println("[arg1: destination Path]");
                break;
            case "rmdir": System.out.println("[arg1: srcPath]");
                break;
            case "mkdir": System.out.println("[arg1: directory name]");
                break;
            case "more":
            case "mv":  System.out.println("[arg1:file name]");
                break;
            case "pipe":  System.out.println("{}");
                break;
            case ">":  System.out.println("");
                break;
            case ">>":  System.out.println("[]");
                break;
            default:
                System.out.println("invalid");
        }



    }

    public static void main(String args[]) throws FileNotFoundException,IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String input="";
        System.out.print(Terminal.srcPath+"> ");
        input=scanner.nextLine();
        while(true){
            if(input.contentEquals("exit")) {
                break;
            }
            else
            {

                Parser parser=new Parser();
                if(!parser.parse(input)) {
                    System.out.println("invalid");
                }
                System.out.print(Terminal.srcPath+"> ");
                input=scanner.nextLine();
            }
        }
    }

}