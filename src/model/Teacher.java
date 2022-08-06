package model;

public class Teacher {
    private String teaId;
    private String name;
    private String address;
    private int contact;

    public Teacher(String teaId, String name, String address, int contact) {
        this.teaId = teaId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Teacher() {
    }

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "teaId='" + teaId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}';
    }
}
