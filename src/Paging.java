import Model.Member;

import java.util.ArrayList;
import java.util.Scanner;

public class Paging {
    static int pageEntries = 2;
    public static void printMemberList(Scanner scanner, ArrayList<Member> list) {
        ArrayList<ArrayList<Member>> listPages = new ArrayList<>();
        int countPage = 0;
        ArrayList<Member> singlePage = new ArrayList<>();
        for (Member member : list) {
            singlePage.add(member);
            countPage += 1;
            if (countPage == pageEntries) {
                listPages.add(singlePage);
                singlePage = new ArrayList<>();
                countPage = 0;
            }
        }
        if (singlePage.size() > 0) {
            listPages.add(singlePage);
        }
        if (listPages.size() > 0) {
            int currentPage = -1;
            String pageInput = "n";
            do {
                if (pageInput.equals("n") && currentPage < listPages.size() - 1) {
                    currentPage += 1;
                    System.out.println("-------------------PAGE " + (currentPage + 1) + "-------------------");
                    printMemberListPage(listPages.get(currentPage));
                }
                if (pageInput.equals("p") && currentPage > 0) {
                    currentPage -= 1;
                    System.out.println("-------------------PAGE " + (currentPage + 1) + "-------------------");
                    printMemberListPage(listPages.get(currentPage));
                }
                String pagingMsg = "";
                if (currentPage < listPages.size() - 1) {
                    pagingMsg = "Press n to go to next page, ";
                }
                if (currentPage > 0) {
                    pagingMsg = pagingMsg + "Press p to go to previous page, ";
                }
                System.out.println(pagingMsg + "Press q to leave");
                pageInput = scanner.nextLine();

            } while (!pageInput.equals("q"));
        }
    }

    public static void printMemberListPage(ArrayList<Member> memberList) {
        for (Member member : memberList) {
            System.out.println("------------Member " + (member.getIndex() + 1) + "------------");
            System.out.println(member.toString());
        }
    }
}
