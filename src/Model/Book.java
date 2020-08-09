package Model;

import java.io.Serializable;
import java.util.Scanner;

public class Book extends Item implements Serializable {
    private String authors;
    private String edition;
    private String ISBN;

    public Book() {

    }

    public Book(int ID, String type, String title, String publication, String year, String language, String subject, int total, String authors, String edition, String ISBN) {
        super(ID, type, title, publication, year, language, subject, total);
        this.authors = authors;
        this.edition = edition;
        this.ISBN = ISBN;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + " item ID: " + ID +
                "\t-\tTitle: " + title +
                "\t-\tEdition: " + edition +
                "\t-\tYear: " + year +
                "\t-\tISBN: " + ISBN +
                "\nAuthors: " + authors +
                "\t-\tPublication: " + publication +
                "\t-\tLanguage: " + language +
                "\nSubject: " + subject +
                "\nAvailable copies: " + available +
                "\t-\tOn-loan copies: " + onLoan;
    }
}
