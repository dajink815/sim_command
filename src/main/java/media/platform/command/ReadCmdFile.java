package media.platform.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author dajin kim
 */
public class ReadCmdFile {
    private static final Logger log = LoggerFactory.getLogger(ReadCmdFile.class);

    public ReadCmdFile() {
        // Do Nothing
    }

    public String read(String filePath) {
        String read = null;
        File file = new File(filePath);
        try(FileReader fr = new FileReader(file)) {
            BufferedReader reader = new BufferedReader(fr);
            read = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("======== FILE DOES NOT EXIST ========");
            //log.error("FileRead.readFile.Exception ", e);
            return filePath;
        }

        return read;
    }
}
