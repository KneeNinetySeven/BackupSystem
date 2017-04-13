package de.knee.backup.fileManagement;

import java.io.File;
import java.util.Arrays;

/**
 * Created by Nils on 10.02.2017.
 * Package: de.knee.backup.fileManagement
 */
public class FileWorker {

    static String[] fileEndings = {"zip", "7z"};

    public static boolean verifyPath(File directory) {
        return directory.exists();
    }

    public static boolean verifyFile(File file) {
        if (file.getName().split("\\." + fileEnding(file)).length > 0) {
            if(!file.getName().split("\\." + fileEnding(file))[0].equals("")){
                return !file.exists();
            }
        }
        return false;
    }

    public static boolean verifyFileEnding(File file) {
        final String ending = fileEnding(file);
        return Arrays.stream(fileEndings).anyMatch(p -> p.equals(ending));
    }

    public static String fileEnding(File file) {
        String sep = "\\.";
        if (file.getName().split(sep).length > 1 && file.getName().contains(".")) {
            return file.getName().split(sep)[file.getName().split(sep).length - 1];
        } else {
            return "";
        }
    }

}
