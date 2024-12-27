import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class App {

    private static int ClassId = 0;
    private String name;

    public App(String name) {
        this.name = name + " " + ClassId;
    }

    public static void main(String[] args) throws Exception {
        // iniitalize
        LaunchTracker tr = new LaunchTracker();
        tr.launchTracker(args);

    }
}
