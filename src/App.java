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
