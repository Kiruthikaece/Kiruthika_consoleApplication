package com.keerthi.lift.login;

import com.keerthi.lift.datalayer.LiftDb;
import com.keerthi.lift.model.Credential;

public class LoginModel {

    private LoginView loginView;

    public LoginModel(LoginView loginView) {
       this.loginView=loginView;
    }

    public boolean validateCredential(Credential credential) {
         if(validateUserName(credential.getUserName())) {
            if(validatePassword(credential.getPassword())) {
                loginView.showMessage("Login Successfull");
                return true;
            }
           
            else
            loginView.showMessage("Invalid Password");
         } else
         loginView.showMessage("Invalid UserName");

         return false;
    }

    private boolean validatePassword(String password) {
       return password.length()>3;
    }

    private boolean validateUserName(String userName) {
        return true;
    }
    

   public void liftInit() {
        LiftDb.getInstance().setUpLift();
    }
}
