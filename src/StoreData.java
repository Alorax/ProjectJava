import java.io.*;
import java.util.ArrayList;

public class StoreData
{
    static ArrayList<Votes> v = new ArrayList<>();
    static BufferedWriter writer;
    static String fileName= "NewProject.txt";

    public StoreData(ArrayList<Votes> v)
    {
        this.v = v;

    }

    public static void writeData()
    {
        try
        {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            for(int index = 0 ; index < v.size();index ++)
            {
                writer.write(v.get(index).toString());
                writer.newLine();
            }
        } catch (IOException ex)
        {
            System.out.println("error: " + ex);
        }
        finally
        {
            try
            {
                writer.flush();
                writer.close();
            } catch (Exception ex)
            {
                System.out.println("error : " + ex);
            }
        }

    }

}