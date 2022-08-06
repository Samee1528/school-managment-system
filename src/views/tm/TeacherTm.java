package views.tm;

import javafx.scene.control.Button;

public class TeacherTm {
    private String teaId;
    private String name;
    private String address;
    private int contact;

    public TeacherTm(String teaId, String name, String address, int contact, Button update) {
        this.teaId = teaId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }


    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getName() { return name; }

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
        return "TeacherTm{" +
                "teaId='" + teaId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                '}';
    }
}
