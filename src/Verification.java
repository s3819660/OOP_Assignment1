import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification {
    public static boolean ISBNVerify(String ISBN) {
        return true;
    }

    public static boolean phoneVerify(String phone) {
        String regex = "^(\\+?)(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static void emailVerify() {

    }

    public static void stringVerify() {

    }
}
