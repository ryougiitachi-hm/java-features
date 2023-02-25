package per.itachi.java.features.nio.watchservice;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WatchServiceEntry {

    public static void main(String[] args) {
        String strPathRegistered = args[0];
        Path pathRegistered = Paths.get(strPathRegistered);
        if (!Files.exists(pathRegistered)) {
            log.error("The specific path {} doesn't exist. ", strPathRegistered);
            return;
        }

        try {
            log.info("Start listening to the directory {}", strPathRegistered);
            WatchService watchService = FileSystems.getDefault().newWatchService();
            pathRegistered.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.OVERFLOW);
            takeWatchService(watchService);
        }
        catch (IOException e) {
            log.error("", e);
        }
    }

    private static void takeWatchService(WatchService watchService) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()->{
            boolean running = true;
            try {
                while (running) {
                    WatchKey watchKey = watchService.take();
                    log.info("It came to a new watch key, {}", watchKey);
                    for (WatchEvent<?> event : watchKey.pollEvents()) {
                        log.info("event={}. ", event);
                    }
                    if(!watchKey.reset()) {
                        log.warn("Failed to reset watch key, which caused polling watch break. ");
                        break;
                    }
                }
            }
            catch (InterruptedException e) {
                log.warn("Thread interrupted. ", e);
            }
        });
    }

}
