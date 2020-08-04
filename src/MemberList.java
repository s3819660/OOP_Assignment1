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

    public static int getMemberListSize() {
        return memberList.size();
    }

    public static void registerMember(Scanner scanner) {
        String[] inputs = new String[6];
        String[] prompts = {"name", "phone", "email", "address", "expire date in the form of dd/MM/yyyy", "status"};

        for (int i = 0; i < 6; i++) {
            System.out.println("Enter " + prompts[i]);
            inputs[i] = scanner.nextLine();
        }

        Date expireDate = null;
        try {
            expireDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputs[4]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Member newMember = new Member(memberList.size(), inputs[0], inputs[1], inputs[2], inputs[3], expireDate, inputs[5]);
        memberList.add(newMember);
    }

    public static void updateMember(Scanner scanner) {
        Date toDay = new Date();
        String[] prompts = {"name", "phone", "email", "address", "status"};

        System.out.println("Enter the member ID you would like to update: ");
        int ID = scanner.nextInt();
        scanner.nextLine(); // flush buffer

        Member member = memberList.get(ID);
        // initiate current info as default
        String[] updates = {member.getName()
                , member.getPhone()
                , member.getEmail()
                , member.getAddress()
                , member.getStatus()};
        //renew membership
//        if (memberList.get(ID - 1).getExpireDate().compareTo(toDay) > 0) {
//            System.out.println("Enter new expire date: ");
//            String dateStr = scanner.nextLine();
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            try {
//                memberList.get(ID).setExpireDate(simpleDateFormat.parse(dateStr));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        } else {
        int check = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println("Current " + prompts[i] + ": " + updates[i]);
            System.out.println("Would you like to update current " + prompts[i] + "? y/n");
            String opt = scanner.nextLine().toLowerCase();
            System.out.println(opt);
            while (true) {
                switch (opt.charAt(0)) {
                    case 'y':
                        System.out.print("Enter new " + prompts[i] + ": ");
                        updates[i] = scanner.nextLine();
                        System.out.println(updates[i]);
                        check = 1;
                        break;
                    case 'n':
                        check = 2;
                        break;
                    default:
                        System.out.println("Invalid option. Please enter y or n: ");
                }
                if (check == 1 || check == 2)
                    break;
            }
        }
        // update member info, cannot use member instance because it is just a clone
        member.setName(updates[0]);
        member.setPhone(updates[1]);
        member.setEmail(updates[2]);
        member.setAddress(updates[3]);
        member.setStatus(updates[4]);

        memberList.set(ID, member); // set memberList(ID - 1) identical as member instance
    }
//    }

    public static void borrow(int memID, int itemID) {
        if (memberList.get(memID).getBorrow().size() < 5) {
            memberList.get(memID).setBorrow(itemID);
        } else System.out.println("You have reached the borrow limit of 5 items.");
    }

    public static boolean returnItem(Scanner scanner, int memID, int itemID, String type) {
        ArrayList<Integer> arrayList = memberList.get(memID).getBorrow();
        if (arrayList.contains(itemID)) {
            int i = arrayList.indexOf(itemID);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println("Enter return date: "); // input return date
            try {
                scanner.nextLine(); // flush buffer
                Date date1 = sdf.parse(scanner.nextLine());
                LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                int index = memberList.get(memID).getBorrow().indexOf(itemID);
                System.out.println(memberList.get(memID).getBorrow().toString());
                System.out.println("itemID = " + (itemID));
                System.out.println("index" + index);
                System.out.println("length = " + memberList.get(memID).getBorrow().size());
                Date date2 = memberList.get(memID).getBorrowDate().get(index);
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
                            System.out.println("expected: " + (memberList.get(memID).getRecord() + lateFee));
                            memberList.get(memID).setRecord(memberList.get(memID).getRecord() + lateFee);
                        }
                        break;
                    case "Journal":
                    case "DVD":
                        if (days > 7) {
                            double lateFee = Math.abs(days - 7) * 0.1;
                            System.out.println("late fee = " + lateFee);
                            memberList.get(memID).setRecord(memberList.get(memID).getRecord() + lateFee);
                        }
                        break;
                }
                memberList.get(memID).getBorrow().remove(i);
                memberList.get(memID).getBorrowDate().remove(i);
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
        if (searchInput.matches("^\\d{2}\\/\\d{2}\\/\\d{4}$")) {
            try {
                // convert String date to Date object
                date = new SimpleDateFormat("dd/MM/yyyy").parse(searchInput);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        // If the keywords are empty, display all items
        if (searchInput.isEmpty()) {
            printMemberList(memberList);
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
            printMemberList(mList);
        }
    }

    public static void printMemberList(ArrayList<Member> list) {
        for (Member member : list) {
            System.out.println("Member" + member.getID());
            System.out.println(member.toString());
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
            e.printStackTrace();
        }

        printMemberList(memberList);
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
