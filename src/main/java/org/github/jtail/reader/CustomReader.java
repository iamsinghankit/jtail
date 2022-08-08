package org.github.jtail.reader;

import java.io.File;
import java.io.RandomAccessFile;

import static java.lang.System.out;

/**
 * @author iamsinghankit
 */
public class CustomReader implements Runnable {
    private static int crunchifyCounter = 0;
    private final boolean shouldIRun = true;
    private final int runEveryNSeconds;
    private final File file;
    private long lastKnownPosition = 0;

    public CustomReader(String myFile, int myInterval) {
        file = new File(myFile);
        this.runEveryNSeconds = myInterval;
    }

    public CustomReader(String myFile) {
        this(myFile, 2000);
    }

    public void run() {
        try {
            while (shouldIRun) {
                Thread.sleep(runEveryNSeconds);
                long fileLength = file.length();
                if (fileLength > lastKnownPosition) {

                    // Reading  file
                    RandomAccessFile readWriteFileAccess = new RandomAccessFile(file, "r");
                    readWriteFileAccess.seek(lastKnownPosition);
                    String crunchifyLine = null;
                    while ((crunchifyLine = readWriteFileAccess.readLine()) != null) {
                        out.println(crunchifyLine);
                        crunchifyCounter++;
                    }
                    lastKnownPosition = readWriteFileAccess.getFilePointer();
                    readWriteFileAccess.close();
                } else {
//                    out.println("Hmm.. Couldn't found new line after line # " + crunchifyCounter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        out.println("Exit the program...");
    }


    public void runNew(){
        try{
            while (shouldIRun){
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
