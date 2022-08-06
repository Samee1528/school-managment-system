package views.tm;

import java.awt.*;

public class ParentTm  {
    private String parId;
    private String name;
    private String address;
    private int contact;
    private Button button;

    public ParentTm() {

    }

    public ParentTm(String parId, String name, String address, int contact, javafx.scene.control.Button update) {
        this.parId = parId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.button = button;
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
        return "ParentTm{" +
                "parId='" + parId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", button=" + button +
                '}';
    }
}
