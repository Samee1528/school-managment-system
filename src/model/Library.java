package model;

public class Library {
    private String libId;
    private String name;
    private String schId;

    public Library(String libId, String name, String schId) {
        this.libId = libId;
        this.name = name;
        this.schId = schId;
    }

    public Library() {
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchId() {
        return schId;
    }

    public void setSchId(String schId) {
        this.schId = schId;
    }

    @Override
    public String toString() {
        return "Library{" +
                "libId='" + libId + '\'' +
                ", name='" + name + '\'' +
                ", schId='" + schId + '\'' +
                '}';
    }
}
