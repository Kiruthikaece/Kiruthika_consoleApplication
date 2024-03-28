package librarymanagement.librarysetup;

import librarymanagement.datalayer.LibraryDatabase;
import librarymanagement.model.Library;

public class LibrarySetupModel {
    
    private LibrarySetupView librarySetupView;

    Library library;

    public LibrarySetupModel(LibrarySetupView librarySetupView) {
       this.librarySetupView = librarySetupView;
       library=LibraryDatabase.getInstance().getLibraray();
    }

    public void startSetup() {
      if(LibraryDatabase.getInstance().getAllLibrary().size()==0) {
        librarySetupView.onFirstSetup();
      }  
      else {
        librarySetupView.onCompleteSetup(LibraryDatabase.getInstance().getAllLibrary().get(0));
        LibraryDatabase.getInstance().insertLibrary(library);
      }
      
    }

    public void validateLibrary(Library library) {
         
        if(!validateEmail(library.getEmail()) || !validatePhone(library.getPhoneNo()) || !validateName(library.getLibraryName())) {
          librarySetupView.showAlert("Invalid library details");
          return;
        }
        else {
          LibraryDatabase.getInstance().insertLibrary(library);
          librarySetupView.onCompleteSetup(library);
          
        }
        
        
    }

    private boolean validateName(String name) {    
      return name.length()>3 && name.length()<50;
     
    }

    private boolean validatePhone(String phoneNo) {
      String regex = "\\d{10}";
      return phoneNo.matches(regex);
    }

    private boolean validateEmail(String email) {
      String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
      return email.matches(regex);
    }

    public int  setCount() {
      if(LibraryDatabase.getInstance().counterLibrary()>0)
       return LibraryDatabase.getInstance().counterLibrary();
      else
      return 0;
      }
}
