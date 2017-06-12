package helpers;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;


public class DBInitReinit {
    public static void runSqlScript(String path) throws FileNotFoundException{
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(path));
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        try{
            String line;
            Process p = Runtime.getRuntime().exec
                    ("psql -U postgres -d persons -h localhost:5432 -f "+path);
            BufferedReader input =
                    new BufferedReader
                            (new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
    }
}
