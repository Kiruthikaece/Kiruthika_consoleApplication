package com.keerthi.interviewpanel.login;

import java.util.Scanner;
import com.keerthi.interviewpanel.InterviewPanelMain;
import com.keerthi.interviewpanel.interviewsetup.InterviewSetupView;
import com.keerthi.interviewpanel.manageInterviewer.ManageInterviewerView;
import com.keerthi.interviewpanel.managecandidate.CandidateView;
import com.keerthi.interviewpanel.model.Credential;

public class LoginView {

    Scanner sc=new Scanner(System.in);
    
    private LoginModel loginModel;

  

    public LoginView() {
        loginModel=new LoginModel(this);
    }
    public void init() {
       System.out.println("==================================================================");
       System.out.println("\t\t"+InterviewPanelMain.getInstance().getAppName());
       System.out.println("\t\t"+"Version "+InterviewPanelMain.getInstance().getVersion());
       System.out.println("==================================================================");
       System.out.println("fetch data....");
       loginModel.retrieveAll();
       System.out.println("\nPlease proceed to login");
       proceedLogin();
   
    }

    private void proceedLogin() {
        Credential credential=new Credential();
        System.out.println("\nEnter username");
        String userName=sc.next();
        credential.setUserName(userName);
        System.out.println("\nEnter password");
        String password=sc.next();
        credential.setPassword(password);
        loginModel.validateLogin(credential);
    }
    public void showAlert(String message) {
       System.out.println("\n"+message);
       checkForLogin();
    }

    private void checkForLogin() {
       System.out.println("\nDo you try again? \nType Yes/No");
       String choice = sc.next();
       if (choice.equalsIgnoreCase("yes")) 
       proceedLogin();
       else if (choice.equalsIgnoreCase("no")) 
       System.out.println("\n ---- Thank You ----");
       else {
       System.out.println("\nInvalid choice, Please enter valid choice.\n");
       checkForLogin();
       }
}

    public void adminMenu() {
       System.out.println("\n\n******   Login Successfull    ******");
       while(true) {
        System.out.println("-------------------------------------------------------");
        System.out.println(" \n 1-Add Candidate \n 2-Add Interviewer \n 3-List of candiadte \n 4-List of Interviewer \n 5-ConductInterview \n 6-search \n 7-Remove \n 8-Logout \n 0-Exit");
        System.out.println("-------------------------------------------------------");
        System.out.println("Enter choice:");
        int choice=sc.nextInt();
        switch(choice) {
            case 1:new CandidateView().addCandidate();
                   break;
            case 2:new ManageInterviewerView().addInterviewer();
                   break;  
            case 3:new CandidateView().listCandidateAll();
                   break;
            case 4:new ManageInterviewerView().listInterviewer();
                   break;
            case 5:new InterviewSetupView().init();
                   break;
            case 6:search();
                   break;
            case 7:remove();
                   break;
            case 8: System.out.println("\n ----  Logout  successfully ----");
                     proceedLogin();
                     return;
            case 0:System.out.println("\n-- Thanks for using " + InterviewPanelMain.getInstance().getAppName() + " --");
                   return;
            default:System.out.println("Invalid choice");
                    return;
        }
       }
       
    }

public void interviewerMenu(Credential credentialInterViewer) {
       System.out.println("\n\n******  Interviewer Login Successfull    ******");
       while(true) {
        System.out.println("-------------------------------------------------------");
        System.out.println("\n 1-Update Status \n 2-Show Candidate \n 3-Logout \n 0-Exit");
        System.out.println("-------------------------------------------------------");
        System.out.println("Enter choice:");
        int choice=sc.nextInt();
        switch(choice) {
            case 1:new InterviewSetupView().updateStatus(credentialInterViewer);
                   break;
            case 2:new CandidateView().listCandidateAll();
                   break;  
            case 3: System.out.println("\n---- Logout  successfully ----");
                    proceedLogin();
                    return;
            case 0:System.out.println("\n-- Thanks for using " + InterviewPanelMain.getInstance().getAppName() + " --");
                   return;
            default:System.out.println("Invalid choice");
                    return;
        }
       }
       
    }

   
    
    public void search() {
       while(true) {
          System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println(" \n 1-search candidate\n 2-search Interview\n 3-Go back\n");
          System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
          System.out.println("Enter your choice");
          int choice=sc.nextInt();
          switch(choice) {
             case 1:new CandidateView().searchCandidate();
                     break;
             case 2: new ManageInterviewerView().searchInterviewer();
                     break;
             case 3: return;
          }
        }
    }

    public void remove() {
       while(true) {
              System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              System.out.println(" \n 1-Remove candidate\n 2-Remove Interview\n 3-Go back\n");
              System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
              System.out.println("Enter your choice");
              int choice=sc.nextInt();
              switch(choice) {
                 case 1:new CandidateView().RemoveCandidate();
                         break;
                 case 2: new ManageInterviewerView().RemoveInterviewer();
                         break;
                 case 3: return;
              }
            }
    }
}
