package views.tm;

import java.awt.*;

public class StudentTm {
    private String stuId;
    private String parId;
    private String name;
    private String address;
    private int contact;
    private Button button;

    public StudentTm() {

    }

    public StudentTm(String stuId, String parId, String name, String address, int contact, javafx.scene.control.Button update) {
        this.stuId = stuId;
        this.parId = parId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.button = button;
    }

    public StudentTm(String teaId, String name, String address, int contact, javafx.scene.control.Button update) {
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
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

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }


    @Override
    public String toString() {
        return "StudentTm{" +
                "stuId='" + stuId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", parId='" + parId + '\'' +
                ", button=" + button +
                '}';
    }
}
