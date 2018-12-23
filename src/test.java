import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class test {

    private static final String FILE_PATH = "C:\\Users\\Puma\\IdeaProjects\\IDSS\\data\\winemag-data-130k-v2.csv";

    @Test
    public void testDataLoading(){
        Preprocessing pre = new Preprocessing(FILE_PATH);
        List<List<Object>> loaded_matrix = pre.loadData();
        int counter = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

            while(reader.ready()){
                reader.readLine();
                counter++;
            }
        }catch (IOException e){

        }

        assertTrue(loaded_matrix.size() == counter);
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}
