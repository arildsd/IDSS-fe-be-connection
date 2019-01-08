import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        //String basePath = new File("").getAbsolutePath();
        //System.out.println(basePath);
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "C:\\Users\\NN_Norway\\AppData\\Local\\Programs\\Python\\Python36\\python.exe C:\\Users\\NN_Norway\\PycharmProjects\\IDSS\\source\\printer.py arg1");
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        while (true) {
            try {
                line = r.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) { break; }
            System.out.println(line);
        }
        System.out.println("Finished!");
    }
}
