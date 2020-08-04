import java.util.Scanner;

public class QuickLibUI {
    public static void printOption(String opt, Scanner scanner) {
        switch (opt) {
            case "1":
                ItemList.searchItem(scanner);
                break;
            case "2":
                ItemList.addItem(scanner);
                break;
            case "3":
                ItemList.updateItem(scanner);
                break;
            case "4":
                MemberList.searchMember(scanner);
                break;
            case "5":
                MemberList.registerMember(scanner);
                break;
            case "6":
                MemberList.updateMember(scanner);
                break;
            case "7":
                ItemList.borrowItem(scanner);
                break;
            case "8":
                ItemList.returnItem(scanner);
                break;
            case "9":
                QuickLib.save();
                System.out.println("Save complete");
                break;
            case "10":
                System.out.println("Exiting");
                QuickLib.save();
                System.exit(0);
            default:
                System.out.println("wrong option");
                break;
        }
    }

    public static void printMenu(Scanner scanner) {
        String opt = "";

        while (true) {
            System.out.print("Welcome to QuickLib!!!\n" +
                    "********************************\n" +
                    "1. Search items by keywords\n" +
                    "2. Add new item\n" +
                    "3. Update item info\n" +
                    "4. Search members by keywords\n" +
                    "5. Register new member\n" +
                    "6. Update member info\n" +
                    "7. Borrow items\n" +
                    "8. Return items\n" +
                    "9. Save data\n" +
                    "10. Quit\n" +
                    "**************************************\n" +
                    "Enter a function (1-10): ");

            opt = scanner.nextLine();
            printOption(opt, scanner);
            System.out.println("Enter to go back to menu");
            scanner.nextLine();
        }
    }

    public static void main(String[] args) {
        QuickLib.start();
        printMenu(new Scanner(System.in));
    }
}
