package com.keerthi.interviewpanel;

import com.keerthi.interviewpanel.datalayer.InterviewDatabase;
import com.keerthi.interviewpanel.login.LoginView;

public class InterviewPanelMain {

    private String appName="Interview Panel Application";

    private String version="0.1.0";

    private static InterviewPanelMain interviewPanel;

    public static InterviewPanelMain getInstance() {
        if(interviewPanel==null)
        interviewPanel=new InterviewPanelMain();

        return interviewPanel;
    }

    public String getAppName() {
        return appName;
    }
    public String getVersion() {
        return version;
    }

    public  void create() {
        LoginView loginview=new LoginView();
         loginview.init();
    }

    public static void main(String[] args) {
        InterviewDatabase.getInstance().counterBook();
        InterviewPanelMain.getInstance().create();
    }
}
