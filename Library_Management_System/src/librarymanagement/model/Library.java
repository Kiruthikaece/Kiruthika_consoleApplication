package librarymanagement.model;

public class Library {
    
    private static int lastlibraryId = 1;
    
    private String libraryName;
    private int libraryId;
    private String email;
    private String phoneNo;
    private String address;

    public void setLibraryId() {
        this.libraryId = lastlibraryId++; 
    }

    public void setLibraryName(String libraryName) {
        this.libraryName=libraryName;
    }
    public String getLibraryName() {
        return libraryName;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getEmail() {
        return email;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo=phoneNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setAddress(String address) {
        this.address=address;
    }

    public String getAddress() {
       return address;
    }
}
