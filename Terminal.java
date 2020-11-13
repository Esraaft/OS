import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Terminal {
	public static void cp(String sourcePath, String destinationPath ) throws FileNotFoundException, IOException{
		File dest=new File(destinationPath);
		destinationPath+="\\";
		if(dest.isDirectory()) {
			String temp="";
			for(int i=sourcePath.length()-1;i>0;i--) {
				if(sourcePath.charAt(i)=='/') {
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
        System.out.println("File copied from " + sourcePath + " to " + destinationPath);
    }
	public static void mv(String sourcePath, String destinationPath)throws IOException {
		//we will move the file by copying it to the destination and then deleting the original file
		Terminal.cp(sourcePath, destinationPath);
		File src=new File(sourcePath);
		src.delete();
	}
	public static void help() {
		System.out.println("GNU bash, version 5.0.17(1)-release (x86_64-pc-linux-gnu)\r\n" + 
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
				" help [-dms] [pattern ...]               { COMMANDS ; }");
	}
	 public static void main(String args[]) throws IOException{	
		
	 }
}

