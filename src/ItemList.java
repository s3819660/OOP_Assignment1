import Model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemList {
    private static ArrayList<Item> itemList = new ArrayList<>();

    public ItemList() {
    }

    public static void searchItem(Scanner scanner) {
        System.out.print("Enter keyword(s): ");
        String searchInput = scanner.nextLine().trim().toLowerCase();

        ArrayList<Item> iList = new ArrayList<>();

        if (searchInput.isEmpty()) {
            Paging.printItemList(scanner, itemList);
        } else {
            for (Item item : itemList) {
                if (item instanceof Book) {
                    Book book = (Book) item;
                    if (book.getTitle().toLowerCase().contains(searchInput) ||
                            book.getPublication().toLowerCase().contains(searchInput) ||
                            book.getYear().toLowerCase().contains(searchInput) ||
                            book.getLanguage().toLowerCase().contains(searchInput) ||
                            book.getSubject().toLowerCase().contains(searchInput) ||
                            book.getISBN().toLowerCase().contains(searchInput)) {
                        iList.add(book);
                    }
                }
                if (item instanceof Journal) {
                    Journal journal = (Journal) item;
                    if (journal.getTitle().toLowerCase().contains(searchInput) ||
                            journal.getPublication().toLowerCase().contains(searchInput) ||
                            journal.getYear().toLowerCase().contains(searchInput) ||
                            journal.getLanguage().toLowerCase().contains(searchInput) ||
                            journal.getSubject().toLowerCase().contains(searchInput) ||
                            journal.getISSN().toLowerCase().contains(searchInput)) {
                        iList.add(journal);
                    }
                }
                if (item instanceof DVD) {
                    DVD dvd = (DVD) item;
                    if (dvd.getTitle().toLowerCase().contains(searchInput) ||
                            dvd.getPublication().toLowerCase().contains(searchInput) ||
                            dvd.getYear().toLowerCase().contains(searchInput) ||
                            dvd.getLanguage().toLowerCase().contains(searchInput) ||
                            dvd.getSubject().toLowerCase().contains(searchInput) ||
                            dvd.getAuthors().toLowerCase().contains(searchInput)) {
                        iList.add(dvd);
                    }
                }
            }
            Paging.printItemList(scanner, iList);
        }
    }

    public static void addItem(Scanner scanner) {
        QuickLib quickLib = new QuickLib();

        System.out.println("Enter 1 for book, 2 for journal and 3 for DVD: ");
        int input = scanner.nextInt();

        do {
            switch (input) {
                case 1:
                    itemList.add(quickLib.addBook(itemList.size()));
                    break;
                case 2:
                    itemList.add(quickLib.addJournal(itemList.size()));
                    break;
                case 3:
                    itemList.add(quickLib.addDVD(itemList.size()));
                    break;
                default:
                    System.out.println("Invalid item type. Please enter again, 1 for book, 2 for journal and 3 for DVD: ");
                    input = 0;
                    break;
            }
        } while (input == 0);
    }

    public static String[] updateItems(Scanner scanner, String[] prompts, String[] updates) {
        for (int i = 0; i < prompts.length; i++) {
            System.out.println("Current " + prompts[i] + ": " + updates[i]);
            System.out.println("Would you like to update current " + prompts[i] + "? y/n");

            String opt;
            do {
                opt = scanner.nextLine().toLowerCase();
                switch (opt) {
                    case "y":
                        System.out.print("Enter new " + prompts[i] + ": ");
                        updates[i] = scanner.nextLine();
                        break;
                    case "n":
                        break;
                    default:
                        System.out.println("Invalid option. Please enter y or n: ");
                }
            } while (!opt.equals("y") && !opt.equals("n"));
        }
        return updates;
    }

    public static void updateBook(Scanner scanner, int ID) {
        String[] prompts = {"title", "publication", "year", "language", "subject", "amount of available copies",
                "authors", "edition", "ISBN"};

        Book book = (Book) itemList.get(ID);
        ///initialize current info as default

        String[] updates = updateItems(scanner, prompts, new String[]{book.getTitle(),
                book.getPublication(),
                book.getYear(),
                book.getLanguage(),
                book.getSubject(),
                Integer.toString(book.getAvailable()),
                book.getAuthors(),
                book.getEdition(),
                book.getISBN()});
        // Update Item info, cannot use item instance because it is just a clone
        book.setTitle(updates[0]);
        book.setPublication(updates[1]);
        book.setYear(updates[2]);
        book.setLanguage(updates[3]);
        book.setSubject(updates[4]);
        book.setAvailable(Integer.parseInt(updates[5]));
        book.setAuthors(updates[6]);
        book.setEdition(updates[7]);
        book.setISBN(updates[8]);

        itemList.set(ID, book);
    }

    public static void updateJournal(Scanner scanner, int ID) {
        String[] prompts = {"title", "publication", "year", "language", "subject", "amount of available copies",
                "ISSN"};

        Journal journal = (Journal) itemList.get(ID);
        ///initialize current info as default

        String[] updates = updateItems(scanner, prompts, new String[]{journal.getTitle(),
                journal.getPublication(),
                journal.getYear(),
                journal.getLanguage(),
                journal.getSubject(),
                Integer.toString(journal.getAvailable()),
                journal.getISSN()});
        // Update Item info, cannot use item instance because it is just a clone
        journal.setTitle(updates[0]);
        journal.setPublication(updates[1]);
        journal.setYear(updates[2]);
        journal.setLanguage(updates[3]);
        journal.setSubject(updates[4]);
        journal.setAvailable(Integer.parseInt(updates[5]));
        journal.setISSN(updates[6]);

        itemList.set(ID, journal);
    }

    public static void updateDVD(Scanner scanner, int ID) {
        String[] prompts = {"title", "publication", "year", "language", "subject", "amount of available copies",
                "authors"};

        DVD dvd = (DVD) itemList.get(ID);
        ///initialize current info as default
        String[] updates = updateItems(scanner, prompts, new String[]{dvd.getTitle(),
                dvd.getPublication(),
                dvd.getYear(),
                dvd.getLanguage(),
                dvd.getSubject(),
                Integer.toString(dvd.getAvailable()),
                dvd.getAuthors()});
        // Update Item info, cannot use item instance because it is just a clone
        dvd.setTitle(updates[0]);
        dvd.setPublication(updates[1]);
        dvd.setYear(updates[2]);
        dvd.setLanguage(updates[3]);
        dvd.setSubject(updates[4]);
        dvd.setAvailable(Integer.parseInt(updates[5]));
        dvd.setAuthors(updates[6]);

        itemList.set(ID, dvd);
    }

    public static void updateItem(Scanner scanner) {
        System.out.println("Enter the item ID you would like to update: ");
        int ID = scanner.nextInt();
        scanner.nextLine();

        if (itemList.get(ID) instanceof Book) {
            updateBook(scanner, ID);
        }
        if (itemList.get(ID) instanceof Journal) {
            updateJournal(scanner, ID);
        }
        if (itemList.get(ID) instanceof DVD) {
            updateDVD(scanner, ID);
        }
    }

    public static void borrowItem(Scanner scanner) {
        System.out.println("Enter member ID: ");
        String memID = LibVerification.memberIDVerify(scanner);
        int memIndex = MemberList.getMemberIndex(memID);

        System.out.println("Enter item ID: ");
        int itemID = LibVerification.itemIDVerify(scanner, itemList.size());

        switch (itemList.get(itemID).getStatus()) {
            case "available":
                MemberList.borrow(memIndex, itemID);
                itemList.get(itemID).setOnLoan(true);
                break;
            case "unavailable":
                System.out.println("This item is currently unavailable");
                break;
        }
    }

    public static void returnItem(Scanner scanner) {
        System.out.println("Enter member ID: ");
        String memID = LibVerification.memberIDVerify(scanner);
        int memIndex = MemberList.getMemberIndex(memID);

        System.out.println("Enter item ID: ");
        int itemID = LibVerification.itemIDVerify(scanner, itemList.size());

        if (MemberList.returnItem(scanner, memIndex, itemID, itemList.get(itemID).getType())) {
            itemList.get(itemID).setOnLoan(false);
        }
    }

    public static void loadItem() {
        try {
            FileInputStream fis = new FileInputStream("items.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            itemList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Save file does not exit, create new file");
        }

        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    public static void saveItem() {
        try {
            FileOutputStream fos = new FileOutputStream("items.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(itemList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
