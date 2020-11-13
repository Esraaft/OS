package com.company;

public class Parser {

    String[] args; // Will be filled by arguments extracted by parse method

    String cmd; // Will be filled by the command extracted by parse method


    /**
     * Return: true if it was able to parse user input correctly. Otherwise false
     * <p>
     * Parameter input: user command
     * <p>
     * In case of success, it should save the extracted command and arguments to
     * args and cmd variables
     * <p>
     * It should also print error messages in case of too few arguments for a
     * commands
     * <p>
     * <p>
     * <p>
     * <p>
     * eg. “cp requires 2 arguments”
     *
     * @param input
     * @return
     */

    public boolean parse(String input) {
        //flag change to false in case cmd or its args is invalid
        boolean flag = true;
        //spilt the given string to its components
        String[] part = input.split("\\s+");
        // for(int i=0;i<part.length;i++) {}
        //checking if the given command and args is valid by if conditions
        //if the first word of the string is a cmd it will check if the args of this cmd is valid
        if (part[0].equals("date")) {
            cmd = part[0];
            //date no args otherwise invalid
            if (1 < part.length) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
        else if (part[0].equals("args")) {
            cmd = part[0];
            if (1 < part.length) {
                flag = false;
                System.out.println("invalid");

            }
            return flag;
        }
        else if (part[0].equals("pwd")) {
            cmd = part[0];
            if (1 < part.length) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
        else if (part[0].equals("rm")) {
            cmd = part[0];
            //takes 1 arg otherwise invalid
            if (1 < part.length) {
                args = new String[]{part[1]};
            }
            if (2 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
        else if (part[0].equals("mv")) {
            //takes 1 arg otherwise invalid
            if (2 < part.length) {
                args = new String[]{part[1], part[2]};
            }
            if (3 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
        }
        else if (part[0].equals("rmdir")) {
            cmd = part[0];
            if (1 < part.length) {
                args = new String[]{part[1]};
            }
            if (2 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
        else if (part[0].equals("mkdir")) {
            cmd = part[0];
            if (1 < part.length) {
                args = new String[]{part[1]};
            }
            if (2 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
        else if (part[0].equals("more")) {
            cmd = part[0];
            if (1 < part.length) {
                args = new String[]{part[1]};
            }
            if (2 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
        else if (part[0].equals("cat")) {
            cmd = part[0];
            //takes []args otherwise invalid
            if (1 < part.length) {
                args = new String[part.length];
                for (int j = 0; j < (part.length) - 1; j++) {
                    args[j] = part[j + 1];
                }
            }
            if (args == null) {
                flag = false;
                System.out.println("invalid");
            }

        }
        else if (part[0].equals("cp")) {
            if (2 < part.length) {
                args = new String[]{part[1], part[2]};
            }
            if (3 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
        }
        else if (part[0].equals("ls")) {
            cmd = part[0];
            if (1 < part.length) {
                args = new String[]{part[1]};
            }
            if (2 < part.length || args == null) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
       else if (part[0].equals("cd")) {
            cmd = part[0];
            //takes no args or 1 arg otherwise invalid
            if (1 < part.length) {
                args = new String[]{part[1]};
            }
            if (2 < part.length) {
                flag = false;
                System.out.println("invalid");
            }
            return flag;
        }
       else if (part[0].equals("help")) {
            cmd = part[0];
            if (1 < part.length) {
                flag = false;
                System.out.println("invalid");

            }
            return flag; }
       //unknown cmd
       else
        {
            flag = false;
            System.out.println("invalid");
        }

        return flag;}




    public String getCmd()
    {
        return cmd;
    }

    public String[] getArguments()
    {
        return  (args) ;
    }


}
