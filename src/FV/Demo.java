package FV;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Demo {
    public static void main(String[] args) throws IOException  {
        Path startingDir = Paths.get("C:\\Users\\ЧипаЧип\\Desktop");
        String fileToSearch = "plano.txt";
        FileSearchExample crawler = new FileSearchExample(fileToSearch, startingDir);
        Files.walkFileTree(startingDir, crawler);
    }
}
