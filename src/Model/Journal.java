package Model;

import java.io.Serializable;

public class Journal extends Item implements Serializable {
    private String ISSN;

    public Journal() {
    }

    public Journal(int ID, String type, String title, String publication, String year, String language, String subject, int total, String ISSN) {
        super(ID, type, title, publication, year, language, subject, total);
        this.ISSN = ISSN;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    @Override
    public String toString() {
        return "[" + type + "]" + " item ID: " + ID +
                "\t-\tTilte: " + title +
                "\t-\tYear: " + year +
                "\t-\tISSN: " + ISSN +
                "\nPublication: " + publication +
                "\t-\tLanguage: " + language +
                "\nSubject: " + subject +
                "\nAvailable copies: " + available +
                "\t-\tOn-loan copies: " + onLoan;
    }
}
