package Model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Member implements Serializable {
    private int ID;
    private String name;
    private String phone;
    private String email;
    private String address;
    private Date expireDate;
    private String status = "active";
    private double record = 0;

    private ArrayList<Integer> borrow = new ArrayList<>();
    private ArrayList<Date> borrowDate = new ArrayList<>();

    public Member() {

    }

    public Member(int ID, String name, String phone, String email, String address, Date expireDate, String status) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.expireDate = expireDate;
        this.status = status;
    }

    // ID is immutable
    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getRecord() {
        return record;
    }

    public void setRecord(double record) {
        this.record = record;
    }

    public ArrayList<Integer> getBorrow() {
        return borrow;
    }

    public void setBorrow(int itemID) {
        this.borrow.add(itemID);
        this.borrowDate.add(new Date());
    }

    public ArrayList<Date> getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(ArrayList<Date> borrowDate) {
        this.borrowDate = borrowDate;
    }

    // There are 5 borrow items at max, therefore we should have a method to display them
    public String getBorrowItem() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < borrow.size(); i++) {
            if (str.isEmpty()) {
                str = "Item ID = " + (borrow.get(i)) + "\t-\tBorrow date:" + sdf.format(borrowDate.get(i)) + "\n";
            } else str = str + ", Item ID = " + (borrow.get(i)) + "\t-\tBorrow date:" + sdf.format(borrowDate.get(i)) + "\n";
        }
        return str;
    }

    @Override
    public String toString() {
        return "ID = " + ID +
                "\nName = " + name +
                "\nPhone number = " + phone +
                "\nEmail = " + email +
                "\nAddress = " + address +
                "\nExpireDate = " + expireDate +
                "\nStatus= " + status +
                "\nRecord = " + record +
                "\nBorrow Items: " + getBorrowItem();
    }
}


