package model;

public class LibraryReader {
    private String libId;
    private String reaId;

    public LibraryReader(String libId, String reaId) {
        this.libId = libId;
        this.reaId = reaId;
    }

    public LibraryReader() {
    }

    public String getLibId() {
        return libId;
    }

    public void setLibId(String libId) {
        this.libId = libId;
    }

    public String getReaId() {
        return reaId;
    }

    public void setReaId(String reaId) {
        this.reaId = reaId;
    }

    @Override
    public String toString() {
        return "LibraryReader{" +
                "libId='" + libId + '\'' +
                ", reaId='" + reaId + '\'' +
                '}';
    }
}
