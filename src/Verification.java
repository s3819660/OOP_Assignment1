import Model.Member;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification {
    public static int IDVerify(Scanner scanner, int size) {
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

    public static String ISBNVerify(Scanner scanner) {
        String ISBN = scanner.nextLine();
        while (true) {
            // check is ISBN contains digit
            try {
                Integer.parseInt(ISBN);
            } catch (Exception e) {
                System.out.println("Invalid ISBN. Please enter again: ");
                continue;
            }
            //check if it contains 10 digit
            if (ISBN.length() != 10) {
                System.out.println("Invalid ISBN. Please enter again: ");
                ISBN = scanner.nextLine();
                continue;
            }

            // Calculate the sum of the digits
            int sum = 0;
            for (int i = 0; i < ISBN.length(); i++) {
                int num = ISBN.charAt(i) - 48;
                sum = sum + num * (10 - i);
            }
            if (sum % 11 == 0) {
                return ISBN;
            } else {
                System.out.println("Invalid ISBN. Please enter again: ");
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
                System.out.println("Invalid ISSN. Please enter again: ");
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
                System.out.println("11 - (sum % 11) = " + (11 - (sum % 11)) + " sum % 11 = " + sum % 11);
                System.out.println("ISSN.charAt(7) = " + ISSN.charAt(8));
                System.out.println("ISSN.charAt(7) - 48 = " + (ISSN.charAt(8) - 48));
                System.out.println("Invalid ISSN. Please enter again: ");
                ISSN = scanner.nextLine();
            }
        }
    }

    public static String phoneVerify(Scanner scanner) {
        String phone = scanner.nextLine();
        String regex = "^(\\+?)(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            Matcher matcher = pattern.matcher(phone);
            if (matcher.matches()) {
                return phone;
            } else {
                System.out.println("Invalid phone number. Please enter again: ");
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
                System.out.println("Invalid email. Please enter again: ");
                email = scanner.nextLine();
            }
        }
    }

    public static String statusVerify(Scanner scanner) {
        String status = scanner.nextLine();
        while (true) {
            if (status.toLowerCase().equals("active") || status.toLowerCase().equals("inactive")) {
                return status.toLowerCase();
            } else {
                System.out.println("Invalid status. Please enter active or inactive");
                status = scanner.nextLine();
            }
        }
    }
}
