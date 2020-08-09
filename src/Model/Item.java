package Model;

import java.io.Serializable;
import java.util.Scanner;

public abstract class Item implements Serializable {
    protected int ID;
    protected String type;
    protected String title;
    protected String publication;
    protected String year;
    protected String language;
    protected String subject;
    protected String status = "available";
    protected int available;
    protected int onLoan = 0;

    public Item() { }

    public Item(int ID, String type, String title, String publication, String year, String language, String subject, int available) {
        this.ID = ID;
        this.type = type;
        this.title = title;
        this.publication = publication;
        this.year = year;
        this.language = language;
        this.subject = subject;
        this.available = available;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStatus() {
        return status;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
        if (this.available == 0) {
            this.status = "unavailable";
        } else this.status = "available";
    }

    public void setOnLoan(boolean bool) {
        if (bool) {
            this.onLoan = this.onLoan + 1;
            setAvailable(this.available - 1);

        } else {
            this.onLoan = this.onLoan - 1;
            setAvailable(this.available + 1);
        }
    }

    public static String[] inputItem() {
        Scanner scanner = new Scanner(System.in);
        String[] inputs = new String[6];
        String[] prompts = {"title: ","publication: ", "year: ", "language: ", "subject: ", "amount of copies: "};
        for (int i = 0; i < 6; i++) {
            System.out.println("Enter " + prompts[i]);
            inputs[i] = scanner.nextLine();
            if (i == 2 || i == 5) {
                while (true) {
                    try {
                        int y = Integer.parseInt(inputs[i]);
                        if (y > 2021 && i == 2) {
                            System.out.println("Invalid year, year exceeded expectation. Please enter again: ");
                        } else break;
                    } catch (Exception e) {
                        System.out.println("Must be number. Please enter again: ");
                    }
                    inputs[i] = scanner.nextLine();
                }
            }
        }
        return inputs;
    }
}
