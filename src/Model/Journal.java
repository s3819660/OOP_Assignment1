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
        return "Model.Journal{" + "ID = " + ID +
                " ISSN='" + ISSN + '\'' +
                ", type=" + type +
                ", title='" + title + '\'' +
                ", publication='" + publication + '\'' +
                ", year=" + year +
                ", language='" + language + '\'' +
                ", subject='" + subject + '\'' +
                ", available=" + available +
                ", onLoan=" + onLoan +
                '}';
    }
}
