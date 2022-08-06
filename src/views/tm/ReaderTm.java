package views.tm;

import javafx.scene.control.Button;

public class ReaderTm {

    private String reaId;
    private String name;
    private String address;
    private int contact;
    private Button button;



    public ReaderTm(String reaId, String name, String address, int contact, Button button) {
        this.reaId = reaId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.button = button;
    }
    public ReaderTm() {
    }

    public ReaderTm(String reaId, String name, String address, String contact, Button update) {
    }

    public String getReaId() {
        return reaId;
    }

    public void setReaId(String reaId) {
        this.reaId = reaId;
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
        return "ReaderTm{" +
                "reaId='" + reaId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", button=" + button +
                '}';
    }
}
