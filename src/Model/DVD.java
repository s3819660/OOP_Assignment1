package Model;

import java.io.Serializable;

public class DVD extends Item implements Serializable {
    private String authors;

    public DVD() { }

    public DVD(int ID, String type, String title, String publication, String year, String language, String subject, int total, String authors) {
        super(ID, type, title, publication, year, language, subject, total);
        this.authors = authors;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Model.DVD{" + "ID = " + ID +
                " authors='" + authors + '\'' +
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
