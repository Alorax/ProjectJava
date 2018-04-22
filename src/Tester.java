import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {

    public static boolean isNumber(String s){
        boolean valid = false;
        try
        {
            Integer.parseInt(s); //s is a valid integer
            valid = true;
        }
        catch (Exception ex)
        {
            //not an integer
        }

        return valid;
    }
}
