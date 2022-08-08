package org.github.jtail.reader;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListenerAdapter;
import org.github.jtail.JTailOption;

/**
 * @author iamsinghankit
 */
public class PollingReader implements JTailReader {
    private final JTailOption jTailOption;

    public PollingReader(JTailOption jTailOption) {
        this.jTailOption = jTailOption;
    }

    @Override
    public void read() {
        Tailer tailer = new Tailer(jTailOption.getFile(), new FileListener(jTailOption), 1000);
        tailer.run();
        Runtime.getRuntime().addShutdownHook(new Thread(tailer::stop));
    }

    private static class FileListener extends TailerListenerAdapter {
        private final int noOfLineToSkip;
        private int skipped;

        FileListener(JTailOption jTailOption) {
            this.noOfLineToSkip = jTailOption.getNoOfLineToSkip();
            this.skipped = 0;
        }

        @Override
        public void handle(String line) {
            if (skipped < noOfLineToSkip) {
                skipped++;
                return;
            }
            System.out.println(line);
        }
    }

}
