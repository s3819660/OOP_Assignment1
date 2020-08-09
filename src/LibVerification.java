import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibVerification {

    public static int itemIDVerify(Scanner scanner, int size) {
        String input = scanner.nextLine();
        while (true) {
            try {
                int ID = Integer.parseInt(input);
                if (ID > size || ID < 0) {
                    System.out.println("Invalid ID. Please enter again: ");
                    input = scanner.nextLine();
                } else return ID;
            } catch (Exception e) {
                System.out.println("Invalid ID. Please enter again: ");
            }
        }
    }

    public static String memberIDVerify(Scanner scanner) {
        String ID = scanner.nextLine();

        while (true) {
            try {
                if (!Pattern.matches("C\\d{7}", ID.toUpperCase())) {
                    System.out.println("Invalid passport Id format. Please enter again: ");
                    ID = scanner.nextLine();

                } else return ID.toUpperCase();
            } catch (Exception e) {
                System.out.println("Invalid passport Id format. Please enter again: ");
            }
        }
    }

    public static String nameVerify(Scanner scanner) {
        String name = scanner.nextLine();
        while (true) {
            try {
                if (!Pattern.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*$", name)) {
                    System.out.println("Invalid name, name only contains letter. Please enter again: ");
                    name = scanner.nextLine();

                } else return name;
            } catch (Exception e) {
                System.out.println("Invalid name, name only contains letter. Please enter again: ");
            }
        }
    }

    public static String phoneVerify(Scanner scanner) {
        String phone = scanner.nextLine().replaceAll(" ", "").replaceAll("-", "");
        String regex = "^(\\+?)(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                return phone;
            } else {
                System.out.println("Invalid phone number format. Please enter again: ");
                phone = scanner.nextLine();
            }
        }
    }

    public static String emailVerify(Scanner scanner) {
        String email = scanner.nextLine();
        String regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        while (true) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return email;
            } else {
                System.out.println("Invalid email format. Please enter again: ");
                email = scanner.nextLine();
            }
        }
    }

    public static String addressVerify(Scanner scanner) {
        String email = scanner.nextLine();
        String regex = "^((\\w+(/\\w+)*)(,)*\\s)+(\\w+)(\\.\\w+)*$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return email;
            } else {
                System.out.println("Invalid address format, must have 2 words. Please enter again: ");
                email = scanner.nextLine();
            }
        }
    }

    public static String ISBNVerify(Scanner scanner) {
        String ISBN = scanner.nextLine().replaceAll("-", "");
        while (true) {
            
            // Check the last digit
            Pattern pattern = Pattern.compile("^\\d{9}(\\d|X)$");
            Matcher matcher = pattern.matcher(ISBN);
            if (!matcher.matches()) {
                System.out.println("Invalid ISBN, valid ISBN contains only 10 numbers or 9 numbers and last digit X. " +
                                    "Please enter again: ");
                ISBN = scanner.nextLine();
                continue;
            }

            // Calculate the sum of the digits
            int sum = 0;
            int num;
            for (int i = 0; i < ISBN.length(); i++) {
                if (i == 9 && ISBN.charAt(i) == 'X') {
                    sum = sum + 10;
                    break;
                }
                num = ISBN.charAt(i) - 48;
                sum = sum + num * (10 - i);
            }

            if (sum % 11 == 0) {
                return ISBN;
            } else {
                System.out.println("Invalid ISBN, sum of digit must mod 11 = 0. Please enter again: ");
                ISBN = scanner.nextLine();
            }
        }
    }

    public static String ISSNVerify(Scanner scanner) {
        String ISSN = scanner.nextLine();
        String regex = "^[0-9]{4}-[0-9]{4}$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            Matcher matcher = pattern.matcher(ISSN);
            if (!matcher.matches()) {
                System.out.println("Invalid ISSN, format, must be ####-####, eg: 0000-0000. Please enter again: ");
                ISSN = scanner.nextLine();
            }

            // Calculate the sum of the digits
            int sum = 0;
            int num;
            for (int i = 0; i < ISSN.length() - 1; i++) {
                if (ISSN.charAt(i) == '-') {
                    continue;
                }
                if (i > 3) {
                    num = ISSN.charAt(i) - 48;
                    sum = sum + num * (8 - (i - 1));
                } else {
                    num = ISSN.charAt(i) - 48;
                    sum = sum + num * (8 - i);
                }
            }
            System.out.println(11 - (sum % 11));
            System.out.println(ISSN.charAt(8) - 48);
            if (sum % 11 == 0 || 11 - (sum % 11) == (ISSN.charAt(8) - 48)) {
                return ISSN;
            } else {
                System.out.println("Invalid ISSN, must follow the formula. Please enter again: ");
                ISSN = scanner.nextLine();
            }
        }
    }

    public static Date dateVerify(Scanner scanner) {
        while (true) {
            try {
                return new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date. Please enter date with format dd/MM/yyyy: ");
            }
        }
    }
}
