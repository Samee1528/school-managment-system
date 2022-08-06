package model;

public class Reader {
    private String reaId;
    private String name;
    private String address;
    private String contact;

    public Reader(String reaId, String name, String address, String contact) {
        this.reaId = reaId;
        this.name = name;
        this.address = address;
        this.contact = contact;
    }

    public Reader(String reaId, String name, String address, int contact) {
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "reaId='" + reaId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
