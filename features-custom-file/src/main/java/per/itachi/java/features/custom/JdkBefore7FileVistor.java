package per.itachi.java.features.custom;

import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdkBefore7FileVistor {

    public List<String> listAllFiles(String directory) {
        File dirBase = new File(directory);
        if (!dirBase.exists() || !dirBase.isDirectory()) {
            log.error("The specific path is invalid, path={}.", directory);
            return Collections.emptyList();
        }

        log.info("Start traversing the base directory, dir={}", directory);
        List<String> listXmlFile = new LinkedList<>();
        List<File> listDir = new LinkedList<>();
        listDir.add(dirBase);
        while (listDir.size() > 0) {
            File dir = listDir.remove(listDir.size() - 1);
            for (File file : dir.listFiles()) {
                if (file.isDirectory()) {
                    listDir.add(file);
                }
                else if (file.isFile() && file.getName().endsWith(".xml")) {
                    listXmlFile.add(file.getAbsolutePath());
                }
            }
        }
        log.info("Finished traversing the base directory, dir={}, number={}. ",
                directory, listXmlFile.size());
        return listXmlFile;
    }
}
