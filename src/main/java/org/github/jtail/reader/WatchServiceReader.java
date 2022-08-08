package org.github.jtail.reader;

import org.github.jtail.JTailOption;

import java.io.IOException;
import java.nio.file.*;

/**
 *  WIP
 * @author iamsinghankit
 */
public class WatchServiceReader implements JTailReader {
    private final JTailOption jTailOption;

    public WatchServiceReader(JTailOption jTailOption) {
        this.jTailOption = jTailOption;
    }

    @Override
    public void read() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = jTailOption.getFile().toPath();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {
                    System.out.println(event);
                }
                key.reset();
            }

            watchService.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
