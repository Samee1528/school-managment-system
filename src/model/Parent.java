package model;

public class Parent {
    private String parId;
    private String name;
    private String address;
    private int contact;

    public Parent(String parId, String name, String address, int contact) {
        this.parId = parId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public String getParId() {
        return parId;
    }

    public void setParId(String parId) {
        this.parId = parId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

}
