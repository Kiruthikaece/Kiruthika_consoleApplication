package librarymanagement.login;

import librarymanagement.datalayer.LibraryDatabase;
import librarymanagement.model.Credentials;

class LoginModel {
    
   private LoginView loginview;

    LoginModel(LoginView loginview) {
    this.loginview= loginview;
   }
  

    public void validateLogin(Credentials credential) {
        if(LibraryDatabase.getInstance().CheckValidLogin(credential))
        loginview.onSuccess(credential.getUserName());
        else
        loginview.showAlert("Invalid user");
       
    }
    
}