package model;

public class School {
    private String schId;
    private String name;
    private String address;

    public School(String schId, String name, String address) {
        this.schId = schId;
        this.name = name;
        this.address = address;
    }

    public School() {
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
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

    @Override
    public String toString() {
        return "School{" +
                "schId='" + schId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
