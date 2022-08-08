package org.github.jtail;

import org.github.jtail.reader.JTailReader;
import org.github.jtail.reader.PollingReader;

/**
 * @author iamsinghankit
 */
public class JTailApp {

    public static void main(String[] args) {
        if (args.length == 0) {
            printHelp();
            exit();
        }
        runCommand(new JTailOption(args));
    }


    private static void runCommand(JTailOption option) {
        if (option.hasHelp()) {
            printHelp();
            exit();
        }
        if (option.hasError()) {
            System.out.println("error: " + option.getErrorMessage() + "\n");
            printHelp();
            exit();
        }
        JTailReader jTailReader = new PollingReader(option);
        jTailReader.read();
    }

    private static void printHelp() {
        String helpMessage = """
                jtail - display the last part of a file.
                             
                Syntax -
                   jtail [-f] [-n] [-h | --help]
                   
                   -f*               file full path
                   -n                no of line to skip from starting
                   -h,--help         print this help message.
                   
                   Note: * required argument.
                """;
        System.out.println(helpMessage);
    }

    private static void exit() {
        System.exit(0);
    }


}
