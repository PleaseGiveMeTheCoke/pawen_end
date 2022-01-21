package save.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteInFile {
    public static void generateAndWrite(String name,String content){
        String s = content;
        FileWriter fw = null;
        File f = new File(name);

        try {
            if(!f.exists()){
                f.createNewFile();
            }
            fw = new FileWriter(f);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(s, 0, s.length()-1);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
