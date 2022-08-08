package org.github.jtail;

import java.io.File;

/**
 * @author iamsinghankit
 */
public class JTailOption {

    private static final String[] VALID_ARGS = {"-h", "--help", "-n", "-f"};
    private File file;
    private int noOfLineToSkip;
    private boolean error;
    private String errorMessage;
    private boolean help;

    public JTailOption(String[] args) {
        try {
            validateArgs(args);
            this.help = getHelp(args);
            this.noOfLineToSkip = getNoOfLineToSkip(args);
            this.file = new File(getFile(args));
        } catch (Exception ex) {
            this.error = true;
            this.errorMessage = ex.getMessage();
        }
    }

    public File getFile() {
        return file;
    }

    public int getNoOfLineToSkip() {
        return noOfLineToSkip;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean hasHelp() {
        return help;
    }


    private void validateArgs(String[] args) {
        for (String arg : args) {
            if (!checkArgs(arg)) {
                throw new IllegalArgumentException("Unknown argument");
            }
        }
    }

    private boolean getHelp(String[] args) {
        for (String arg : args) {
            if (arg.equalsIgnoreCase("-h") || arg.equalsIgnoreCase("--help")) {
                return true;
            }
        }
        return false;
    }

    private int getNoOfLineToSkip(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-n")) {
                if ((i + 1) >= args.length) {
                    throw new IllegalArgumentException("No of line to skip required");
                }
                return Integer.parseInt(args[i + 1]);
            }
        }
        return 0;
    }

    private String getFile(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase("-f")) {
                if ((i + 1) >= args.length) {
                    throw new IllegalArgumentException("File required");
                }
                return args[i + 1];
            }
        }
        throw new IllegalArgumentException("-f option not specified");
    }

    private boolean checkArgs(String arg) {
        for (String a : VALID_ARGS) {
            if (a.equalsIgnoreCase(arg)) {
                return true;
            }
        }
        return false;
    }


}
