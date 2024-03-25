package com.keerthi.interviewpanel.login;

import com.keerthi.interviewpanel.datalayer.InterviewDatabase;
import com.keerthi.interviewpanel.model.Credential;

public class LoginModel {

    private LoginView loginView;

    public LoginModel(LoginView loginView) {
        this.loginView=loginView;
    }

    public void validateLogin(Credential credential) {
        if(InterviewDatabase.getInstance().CheckValidLogin(credential))
        {
            if(InterviewDatabase.getInstance().findAdminorInterviewer(credential))
            loginView.adminMenu();
            else
            loginView.interviewerMenu(credential);
        }
        else
        loginView.showAlert("Invalid user");
    } 
}
