import Model.*;

import java.util.Scanner;

public class QuickLib {
    public Book addBook(int ID) {
        Scanner scanner = new Scanner(System.in);
        String authors;
        String edition;
        String ISBN;
        String[] itemField = Item.inputItem();

        System.out.println("Enter authors: ");
        authors = scanner.nextLine();

        System.out.println("Enter edition: ");
        edition = scanner.nextLine();

        System.out.println("Enter ISBN: ");
        ISBN = Verification.ISBNVerify(scanner);

        return new Book(ID, "Book", itemField[0], itemField[1], itemField[2], itemField[3], itemField[4],
                Integer.parseInt(itemField[5]), authors, edition, ISBN);
    }

    public Journal addJournal(int ID) {
        Scanner scanner = new Scanner(System.in);
        String ISSN;
        String[] itemField = Item.inputItem();

        System.out.println("Enter ISSN: ");
        ISSN = Verification.ISSNVerify(scanner);

        return new Journal(ID, "Journal", itemField[0], itemField[1], itemField[2], itemField[3],
                itemField[4], Integer.parseInt(itemField[5]), ISSN);
    }

    public DVD addDVD(int ID) {
        Scanner scanner = new Scanner(System.in);
        String authors;
        String[] itemField = Item.inputItem();

        System.out.println("Enter authors: ");
        authors = scanner.nextLine();

        return new DVD(ID, "DVD", itemField[0], itemField[1], itemField[2], itemField[3], itemField[4],
                Integer.parseInt(itemField[5]), authors);
    }

    public static void start() {
        ItemList.loadItem();
        MemberList.loadMember();
    }

    public static void save() {
        ItemList.saveItem();
        MemberList.saveMember();
    }
}
