import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Parser {
    String[] args; // Will be filled by arguments extracted by parse method
    String cmd; // Will be filled by the command extracted by parse method

    public boolean parse(String input) throws FileNotFoundException,IOException {
        //flag change to false in case cmd or its args is invalid
        boolean flag = true;
        //spilt the given string to its components
        String[] part = input.split("\\s+");
        //checking if the given command and args is valid by if conditions
        //if the first word of the string is a cmd it will check if the args of this cmd is valid
        if (part[0].equals("date")) {
            cmd = part[0];
            //date no args otherwise invalid
            if (1 < part.length) {
                flag = false;
            }
            else {
            	Terminal.date();
            }
        }
        else if (part[0].equals("args")) {
            cmd = part[0];
            if (1 < part.length) {
                flag = false;
            }
            else {
            	Terminal.args();
            }
        }
        else if (part[0].equals("pwd")) {
            cmd = part[0];
            if (1 < part.length) {
                flag = false;
            }
            else {
            	Terminal.pwd();	
            }
        }
        else if (part[0].equals("rm")) {
            cmd = part[0];
            //takes 1 arg otherwise invalid
            if ( part.length==2) {
            	File newFile=new File(part[1]);
            	if(newFile.isFile()) {
                args = new String[]{part[1]};
                Terminal.rm(part[1]);
                }
            }
            else if(part.length==3) {
                File newFile=new File(part[2]);
                if(part[1].contentEquals("-r")&&newFile.isDirectory())
                {
                	args = new String[]{part[2]};
                    Terminal.rm(part[2]);
                }
            }
            else {
                flag = false;
            }
        }
        else if (part[0].equals("mv")) {
            //takes 1 arg otherwise invalid
            if (part.length==3) {
            	if(part[1].charAt(1)!=':') {
        			String temp=Terminal.srcPath+"\\"+part[1];
        			part[1]=temp;
        		}
        		if(part[2].charAt(1)!=':') {
        			String temp=Terminal.srcPath+"\\"+part[2];
        			part[2]=temp;
        		}
        		File newFile=new File(part[1]);
        		File newFile2=new File(part[2]);
        		if(newFile.exists()&&newFile2.exists()) {
                args = new String[]{part[1], part[2]};
                Terminal.mv(part[1], part[2]);}
        		else
        			flag=false;
            }
            else {
                flag = false;
            }
        }
        else if (part[0].equals("rmdir")) {
            cmd = part[0];
            //takes []args otherwise invalid
            if (part.length==2) {
            	File newFile=new File(part[1]);
            	if(newFile.isDirectory()) {
                args = new String[]{part[1]};
            	Terminal.rmdir(part[1]);
            	flag=true;
            	}
            	else
            		flag=false;
            }
            else {
                flag = false;
            }
        }
        else if (part[0].equals("mkdir")) {
            cmd = part[0];
            if (part.length==2) {
            	File newFile=new File(part[1]);       	
                args = new String[]{part[1]};
            	Terminal.mkdir(part[1]);
            	flag=true;
            	}
            else{
                flag = false;
            }
        }
        else if (part[0].equals("more")) {
            cmd = part[0];
            if (part.length==2) {
                args = new String[]{part[1]};
                Terminal.more(part[1]);
                flag=true;                
            	}
            	else{
                flag = false;
            }
        }
        else if (part[0].equals("cat")) {
            cmd = part[0];
            //takes []args otherwise invalid
            if (1 < part.length) {
                args = new String[part.length-1];
                for (int j = 0; j < (part.length) - 1; j++) {
                    args[j] = part[j + 1];
                }
                Terminal.cat(args);
            }
            else {
                flag = false;
            }
            
        }
        else if (part[0].equals("cp")) {
            if (2 < part.length) {
                args = new String[]{part[1], part[2]};
                Terminal.cp(part[1], part[2]);
            }
            else {
                flag = false;
            }
        }
        else if (part[0].equals("ls")) {
            cmd = part[0];

            if (1 < part.length ) {
                flag = false;
            }
            else {
            	Terminal.ls();
            }
        }
       else if (part[0].equals("cd")) {
            cmd = part[0];
            //takes  1 arg otherwise invalid
            if (part.length==2) {
                args = new String[]{part[1]};
                Terminal.cd(part[1]);
            }
            else  {
                flag = false;
            }
        }
       else if (part[0].equals("help")) {
            cmd = part[0];
            if (1 < part.length) {
                flag = false;

            }
            else{
            Terminal.help();	
            }
            }
       else if(part[0].equals("clear")) {
    	   cmd=part[0];
    	   if(1<part.length) {
    		   flag=false;
    	   }
    	   else {
    		   Terminal.clear();
    	   }
       }
        //unknown cmd
       else
        {
            flag = false;
            System.out.println("invalid");
        }

        return flag;
    }




    public String getCmd()
    {
        return cmd;
    }

    public String[] getArguments()
    {
        return  (args) ;
    }


}