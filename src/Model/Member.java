package Model;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Member implements Serializable {
    private int index;
    private String ID;
    private Date expireDate;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String status = "active";
    private double record = 0;

    private ArrayList<Integer> borrow;
    private ArrayList<Date> borrowDate;

    public Member(int index, String name, String ID, Date expireDate, String phone, String email, String address, String status) {
        this.index = index;
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.expireDate = expireDate;
        this.status = status;
        this.borrow = new ArrayList<>();
        this.borrowDate = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getIndex() {
        return index;
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

    // There are 5 borrow items at max, therefore we should have a method to display them
    public String getBorrowItem() {
        StringBuilder str = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < borrow.size(); i++) {
            if (str.length() == 0) {
                str = new StringBuilder("Item ID = " + (borrow.get(i)) + "\t-\tBorrow date:" + sdf.format(borrowDate.get(i)) + "\n");
            } else str.append(", Item ID = ").append(borrow.get(i)).append("\t-\tBorrow date:").append(sdf.format(borrowDate.get(i))).append("\n");
        }
        return str.toString();
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = new DecimalFormat("#0.0");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return "index = " + index +
                "\nID: " + ID +
                "\nName: " + name +
                "\nPhone number: " + phone +
                "\nEmail: " + email +
                "\nAddress: " + address +
                "\nExpire date: " + simpleDateFormat.format(expireDate) +
                "\nStatus: " + status +
                "\nRecord: " + numberFormat.format(record) +
                "\nBorrow item IDs: " + getBorrowItem();
    }
}


