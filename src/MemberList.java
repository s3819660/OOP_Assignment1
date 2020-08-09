import Model.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MemberList {
    private static ArrayList<Member> memberList = new ArrayList<>();

    public MemberList() {
    }

    public static int getMemberIndex(String ID) {
        for (Member member : memberList) {
            if (member.getID().compareTo(ID) == 0)
                return member.getIndex();
        }
        return -1;
    }

    public static void registerMember(Scanner scanner) {

        System.out.println("Enter name: ");
        String name = LibVerification.nameVerify(scanner);

        System.out.println("Enter ID: ");
        String ID = LibVerification.memberIDVerify(scanner);

        System.out.println("Enter expire date: ");
        Date date = LibVerification.dateVerify(scanner);

        System.out.println("Enter phone: ");
        String phone = LibVerification.phoneVerify(scanner);

        System.out.println("Enter email: ");
        String email = LibVerification.emailVerify(scanner);

        System.out.println("Enter address: ");
        String address = LibVerification.addressVerify(scanner);

        Member newMember = new Member(memberList.size(), name, ID, date, phone, email, address, "active");
        memberList.add(newMember);
    }

    public static void updateMember(Scanner scanner) {
        final String[] PROMPTS = {"ID", "expire date in the form of dd/MM/yyyy", "name", "phone", "email", "address"};
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter the member ID you would like to update: ");
        String ID = LibVerification.memberIDVerify(scanner);

        int index = MemberList.getMemberIndex(ID);
        Member member = memberList.get(index);
        String dateStr = simpleDateFormat.format(member.getExpireDate());
        // initiate current info as default
        String[] updates = {member.getID(),
                            dateStr,
                            member.getName(),
                            member.getPhone(),
                            member.getEmail(),
                            member.getAddress(),
                            member.getStatus()};

        for (int i = 0; i < PROMPTS.length; i++) {
            System.out.println("Current " + PROMPTS[i] + ": " + updates[i]);
            System.out.println("Would you like to update current " + PROMPTS[i] + "? y/n");
            String opt;
            do {
                opt = scanner.nextLine().toLowerCase();
                switch (opt) {
                    case "y":
                        System.out.print("Enter new " + PROMPTS[i] + ": ");
                        if (i == 1) {
                            date = LibVerification.dateVerify(scanner);
                        } else {
                            updates[i] = scanner.nextLine();
                        }
                        break;
                    case "n":
                        break;
                    default:
                        System.out.println("Invalid option. Please enter y or n: ");
                }
            } while (!opt.equals("y") && !opt.equals("n"));
        }

        // update member info, cannot use member instance because it is just a clone
        member.setID(updates[0]);
        member.setExpireDate(date);
        member.setName(updates[1]);
        member.setPhone(updates[2]);
        member.setEmail(updates[3]);
        member.setAddress(updates[4]);
        member.setStatus("active");

        memberList.set(index, member); // set memberList(index) identical as member instance
    }

    public static void borrow(int memIndex, int itemID) {
        if (memberList.get(memIndex).getBorrow().size() < 5) {
            memberList.get(memIndex).setBorrow(itemID);
        } else System.out.println("You have reached the borrow limit of 5 items.");
    }

    public static boolean returnItem(Scanner scanner, int memIndex, int itemID, String type) {
        ArrayList<Integer> arrayList = memberList.get(memIndex).getBorrow();
        if (arrayList.contains(itemID)) {
            int i = arrayList.indexOf(itemID);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter return date: "); // input return date
            try {
                scanner.nextLine(); // flush buffer
                Date date1 = sdf.parse(scanner.nextLine());
                LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int index = memberList.get(memIndex).getBorrow().indexOf(itemID);
                System.out.println(memberList.get(memIndex).getBorrow().toString());
                System.out.println("itemID = " + (itemID));
                System.out.println("index" + index);
                System.out.println("length = " + memberList.get(memIndex).getBorrow().size());
                Date date2 = memberList.get(memIndex).getBorrowDate().get(index);
                LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                // calculate numbers of days between borrow and return date
                int days = (int) (ChronoUnit.DAYS.between(localDate2, localDate1));
                System.out.println(days);
                System.out.println(type);
                switch (type) {
                    case "Book":
                        if (days > 14) {
                            double lateFee = Math.abs(days - 14) * 0.1;
                            System.out.println("late fee = " + lateFee);
                            System.out.println("expected: " + (memberList.get(memIndex).getRecord() + lateFee));
                            memberList.get(memIndex).setRecord(memberList.get(memIndex).getRecord() + lateFee);
                        }
                        break;
                    case "Journal":
                    case "DVD":
                        if (days > 7) {
                            double lateFee = Math.abs(days - 7) * 0.1;
                            System.out.println("late fee = " + lateFee);
                            memberList.get(memIndex).setRecord(memberList.get(memIndex).getRecord() + lateFee);
                        }
                        break;
                }
                memberList.get(memIndex).getBorrow().remove(i);
                memberList.get(memIndex).getBorrowDate().remove(i);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("true");
            return true;
        } else {
            System.out.println("Member did not borrow this item");
            return false;
        }
    }

    public static void searchMember(Scanner scanner) {
        System.out.print("Enter keyword(s): ");
        String searchInput = scanner.nextLine();

        // Compare date keyword
        // check if the input matches the date format
        Date date = null;
        if (searchInput.matches("^\\d{2}/\\d{2}/\\d{4}$")) {
            try {
                // convert String date to Date object
                date = new SimpleDateFormat("dd/MM/yyyy").parse(searchInput);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // If the keywords are empty, display all items
        if (searchInput.isEmpty()) {
            Paging.printMemberList(scanner, memberList);
        } else {
            ArrayList<Member> mList = new ArrayList<>();
            for (Member member : memberList) {
                // make sure the keywords can be found regardless of their case
                if (member.getName().toLowerCase().contains(searchInput.toLowerCase())
                        || member.getPhone().contains(searchInput)
                        || member.getEmail().toLowerCase().contains(searchInput.toLowerCase())
                        || member.getAddress().toLowerCase().contains(searchInput.toLowerCase())
                        || member.getExpireDate().compareTo(date) == 0
                        || member.getStatus().toLowerCase().contains(searchInput.toLowerCase())
                ) {
                    mList.add(member);
                }
            }
            Paging.printMemberList(scanner, mList);
        }
    }

    public static void loadMember() {
        try {
            FileInputStream fis = new FileInputStream("members.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);

            memberList = (ArrayList) ois.readObject();

            ois.close();
            fis.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Save file does not exit, create new file");
        }

        Paging.printMemberListPage(memberList);
    }

    public static void saveMember() {
        try {
            FileOutputStream fos = new FileOutputStream("members.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(memberList);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
