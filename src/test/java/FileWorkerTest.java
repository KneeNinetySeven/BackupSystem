import de.knee.backup.fileManagement.FileWorker;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileWorkerTest extends TestCase {

    FileWorker worker = new FileWorker();

    @Test
    public void testVerifyPath() {
        String pathname = "test";
        File file = new File(pathname);

        if (!file.exists()) file.mkdir();
        assertEquals(worker.verifyPath(file), file.isDirectory());

        file.delete();
    }

    @Test
    public void testVerifyFileNonUsableNames() {
        File file7z = new File(".7z");
        File fileZip = new File(".zip");

        if (!file7z.exists()) {
            try {
                file7z.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileZip.exists()) {
            try {
                fileZip.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assertFalse(worker.verifyFile(file7z));
        assertFalse(worker.verifyFile(fileZip));

        fileZip.delete();
        file7z.delete();
    }

    @Test
    public void testVerifyFileNoExtension() {
        File file = new File("testFile");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertFalse(worker.verifyFile(file));
        file.delete();
    }

    @Test
    public void testVerifyFileNonExisting() {
        File file7z = new File("test.7z");
        File fileZip = new File("test.zip");

        assertTrue(worker.verifyFile(file7z));
        assertTrue(worker.verifyFile(fileZip));

        file7z.delete();
        fileZip.delete();
    }

    @Test
    public void testVerifyFileExisting() {
        File file7z = new File("test.7z");
        File fileZip = new File("test.zip");

        if (!file7z.exists()) {
            try {
                file7z.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!fileZip.exists()) {
            try {
                fileZip.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        assertFalse(worker.verifyFile(file7z));
        assertFalse(worker.verifyFile(fileZip));

        file7z.delete();
        fileZip.delete();
    }

    @Test
    public void testFileEndingMultipleDots() {
        File file = new File("hallo.welt.neue.datei");
        if (!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(worker.fileEnding(file), "datei");

        file.delete();
    }

    @Test
    public void testFileEndingNone() {
        File file = new File("hallo");
        if(!file.exists()) try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(worker.fileEnding(file), "");

        file.delete();
    }

    @Test
    public void testVerifyFileEnding(){
        File fileZ = new File("hallo.welt.zip");
        File file7 = new File("hallo-welt.7.7z");
        File fileX = new File("hallo-welt.7.X");

        if(!fileZ.exists()) try {
            fileZ.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }


        if(!file7.exists()) try {
            file7.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!fileX.exists()) try {
            fileX.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertTrue(worker.verifyFileEnding(file7));
        assertTrue(worker.verifyFileEnding(fileZ));
        assertFalse(worker.verifyFileEnding(fileX));

        file7.delete();
        fileZ.delete();
        fileX.delete();

    }
}
