import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class test {

    private static final String FILE_PATH = new File("").getAbsolutePath() + "\\data\\winemag-data-130k-v2.csv";

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

    @Test
    public void testLineSplitter(){
        String s = "2,US,\"Tart and snappy, the \",,87,14.0,Oregon,Willamette Valley,Willamette Valley,Paul Gregutt,@paulgwine ,Rainstorm 2013 Pinot Gris (Willamette Valley),Pinot Gris,Rainstorm";
        String[] arrS = {"2", "US", "Tart and snappy, the ", "", "87", "14.0", "Oregon", "Willamette Valley", "Willamette Valley", "Paul Gregutt", "@paulgwine ", "Rainstorm 2013 Pinot Gris (Willamette Valley)", "Pinot Gris,Rainstorm"};
        List<String> manualSplit = new ArrayList<>();
        manualSplit.addAll(Arrays.asList(arrS));

        Preprocessing pre = new Preprocessing(FILE_PATH);
        List<String> sList = pre.custom_split(s);
        assertEquals(manualSplit.size(), sList.size());
        for (int i = 0; i < sList.size(); i++){
            assertEquals(manualSplit.get(i) ,sList.get(i));
        }
    }



    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}
