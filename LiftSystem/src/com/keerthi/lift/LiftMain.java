package com.keerthi.lift;

import com.keerthi.lift.login.LoginView;

public class LiftMain {

    private String appName="Lift System";

    private static LiftMain liftMain;
   
    public  String getAppName() {
        return appName;
     }

     public static LiftMain getInstance() {
        if(liftMain==null)
        liftMain=new LiftMain();
        return liftMain;
     }

     public void create() {
        LoginView loginView=new LoginView();
        loginView.init();
     }
    public static void main(String[] args)  {
        
        LiftMain.getInstance().create();
    }
}
