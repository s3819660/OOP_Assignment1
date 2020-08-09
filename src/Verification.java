import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Verification {
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
                    System.out.println("Invalid ID. Please enter again: ");
                    ID = scanner.nextLine();

                } else return ID.toUpperCase();
            } catch (Exception e) {
                System.out.println("Invalid ID. Please enter again: ");
            }
        }
    }

    public static String nameVerify(Scanner scanner) {
        String name = scanner.nextLine();

        while (true) {
            try {
                if (!Pattern.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)+$", name)) {
                    System.out.println("Invalid ID. Please enter again: ");
                    name = scanner.nextLine();

                } else return name;
            } catch (Exception e) {
                System.out.println("Invalid name, name only contain letter, Please enter again: ");
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

    public static String addressVerify(Scanner scanner) {
        String email = scanner.nextLine();
        String regex = "^((\\w+(\\/\\w+)*)(\\,)*\\s)+(\\w+)(\\.\\w+)*$";
        Pattern pattern = Pattern.compile(regex);
        while (true) {
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return email;
            } else {
                System.out.println("Invalid address. Please enter again: ");
                email = scanner.nextLine();
            }
        }
    }

    public static String ISBNVerify(Scanner scanner) {
        String ISBN = scanner.nextLine().replaceAll("-", "");
        while (true) {
//            // check is ISBN contains digit
//            try {
//                Integer.parseInt(ISBN);
//            } catch (Exception e) {
//                System.out.println("Invalid ISBN. Please enter again: ");
//                continue;
//            }
            //check if it contains 10 digit
            if (ISBN.length() != 10) {
                System.out.println("Invalid ISBN. Please enter again: ");
                ISBN = scanner.nextLine();
                continue;
            }
            char lastChar = ISBN.charAt(9);
            
            // Check the last digit
            if ((lastChar < 48 && lastChar > 57) && lastChar != 'X') {
                System.out.println("Invalid ISBN. Please enter again: ");
                ISBN = scanner.nextLine();
                continue;
            }

            // Calculate the sum of the digits
            int sum = 0;
            int num;
            for (int i = 0; i < ISBN.length(); i++) {
                if (i == 0 && ISBN.charAt(i) == 'X') {
                    sum = sum + 10;
                    break;
                }
                num = ISBN.charAt(i) - 48;
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

//    public static String statusVerify(Scanner scanner) {
//        String status = scanner.nextLine();
//        while (true) {
//            if (status.toLowerCase().equals("active") || status.toLowerCase().equals("expired")) {
//                return status.toLowerCase();
//            } else {
//                System.out.println("Invalid status. Please enter active or expired");
//                status = scanner.nextLine();
//            }
//        }
//    }

    public static Date dateVerify(Scanner scanner) {
        while (true) {
            try {
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(scanner.nextLine());
                return date;
            } catch (ParseException e) {
                System.out.println("Invalid date. Please enter date with formal dd/MM/yyyy: ");
            }
        }
    }
}
