package com.keerthi.lift.login;

import java.util.Scanner;

import com.keerthi.lift.LiftMain;
import com.keerthi.lift.managelift.ManageLiftView;
import com.keerthi.lift.model.Credential;

public class LoginView {
     
    private LoginModel loginModel;
    Scanner sc=new Scanner(System.in);

    public LoginView() {
        loginModel=new LoginModel(this);
    }

    public void init() {
       System.out.println("====================================================");
       System.out.println(LiftMain.getInstance().getAppName());
       System.out.println("====================================================");
       System.out.println("Processed to Login");
       proceedLogin();
    }

    private void proceedLogin() {
       Credential credential = new Credential();
       System.out.println("Enter Username");
       credential.setUserName(sc.next());
       System.out.println("Enter Password");
       credential.setPassword(sc.next());
       if(loginModel.validateCredential(credential)) {
        loginModel.liftInit();
        liftMenu();
       }
    }

    public void showMessage(String message) {
       System.out.println(message);
      
    }

   

    private void liftMenu() {
        while(true) {
            System.out.println("\n1-Display Lift Position \n2-Assign Lift \n3-Set Maintance \n0-exit");
            System.out.println("Enter your choice:");
            int choice=sc.nextInt();
            switch(choice) {
                case 1:new ManageLiftView().display();
                        break;
                case 2:new ManageLiftView().assignLift();
                      break;
                case 3:new ManageLiftView().setMaintance();
                       break;
                case 0:return;
            }
        }
    }


}
