package com.keerthi.railway;

import com.keerthi.railway.login.LoginView;

public class RailwayMain {
   

    private static RailwayMain railwayMain;


    public static RailwayMain getInstance() {
        if(railwayMain==null)
        railwayMain=new RailwayMain();

        return railwayMain;
    }

    public void init() {
        LoginView loginView=new LoginView();
        loginView.createSetUp();
    }
    public static void main(String[] args) {
        RailwayMain.getInstance().init();
    }
}
