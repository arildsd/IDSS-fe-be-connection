import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Preprocessing {

    /*
    Data information:
    header: country, description, designation, points, price, province, region_1, region_2, taster_name, taster_twitter_handle, title, variety, winery
    types: String, String, String, int, int, String, String, String, String, String, String, String, String
     */

    String filePath;
    BufferedReader reader;
    public static final String delimiter = ",";

    public Preprocessing(String filePath){
        this.filePath = filePath;
        try{
            this.reader = new BufferedReader(new FileReader(filePath));
        }catch (IOException e){
            System.err.print("Failed to read the file with path " + filePath);
        }
    }

    public List<List<Object>> loadData(){
        List<List<Object>> table = new ArrayList<>();
        // Read header line
        try{
            reader.readLine();
        }catch (IOException e){
            System.err.println("Failed to read header line.");
        }


        while(true){
            // Read a single line of the data
            String line = null;
            try{
                line = reader.readLine();
            }catch (IOException e){
                System.err.println("Failed to read a line.");
                break;
            }

            // End the loop if all the lines have been read
            if (line.equals("")){
                break;
            }

            List<Object> lineList = new ArrayList<>();
            lineList.addAll(custom_split(line));

            // Convert data types
            for(int i = 0; i < lineList.size(); i++){
                // Insert null values for missing values
                if("".equals(lineList.get(i))){
                    lineList.set(i, null);
                }
                // Convert points and price to Integer values
                else if (i == 4 || i == 5){
                    lineList.set(i, Double.valueOf((String) lineList.get(i)));
                }

            }
        }
        try{
            reader.close();
        }catch (IOException e){
            System.err.println("Failed to close the file with path " + filePath);
        }

        return table;
    }

    List<String> custom_split(String line){
        /*
        Method for splitting the columns in a csv that have text containing commas that should be ignored.
         */
        List<Integer> split_indexes = new ArrayList<>();
        boolean skipMode = false;
        for (int i = 0; i < line.length(); i++){
            char ch = line.charAt(i);
            if(skipMode){
                if (ch == '"'){
                    skipMode = false;
                }
            }else {
                if(ch == ','){
                    split_indexes.add(i);
                }else if(ch == '"'){
                    skipMode = true;
                }
            }
        }
        List<String> result = new ArrayList<>();
        result.add(line.substring(0, split_indexes.get(0)));
        for (int i = 0; i < split_indexes.size()-1; i++) {
            // Handle empty values
            if (split_indexes.get(i)+1 == split_indexes.get(i+1)){
                result.add(null);
            }else {
                String text = line.substring(split_indexes.get(i)+1, split_indexes.get(i+1));
                result.add(text);
            }

        }
        result.add(line.substring(split_indexes.get(split_indexes.size()-1)));
        return result;
    }

    //private groupBy
}
