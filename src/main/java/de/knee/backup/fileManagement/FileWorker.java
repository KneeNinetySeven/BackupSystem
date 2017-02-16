package de.knee.backup.fileManagement;

import java.io.File;

/**
 * Created by Nils on 10.02.2017.
 * Package: de.knee.backup.fileManagement
 */
public class FileWorker {

    public static boolean verifyPath(File directory){
        return directory.exists();
    }


}
