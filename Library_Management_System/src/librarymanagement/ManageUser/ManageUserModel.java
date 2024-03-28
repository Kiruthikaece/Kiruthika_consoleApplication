package librarymanagement.ManageUser;

import librarymanagement.datalayer.LibraryDatabase;
import librarymanagement.model.User;

public class ManageUserModel {


    private ManageUserView manageUserView;

    public ManageUserModel(ManageUserView manageUserView) {
      this.manageUserView=manageUserView;
    }

    public void validateAndAddUser(User user) {
       if(LibraryDatabase.getInstance().inserUserinDb(user) && validateName(user.getName())
       && validatePhone(user.getPhoneNo()) && validateEmail(user.getEmailId())) {
        manageUserView.onSuccessAddUser(user);
       }
       else
       manageUserView.onExistUser(user);
        
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

    public void getAll() {
      manageUserView.printUser(LibraryDatabase.getInstance().getallUsers());
    }
    
    public int  setCount() {
      if(LibraryDatabase.getInstance().counterUser()>0)
       return LibraryDatabase.getInstance().counterUser();
      else
      return 0;
      }
}
