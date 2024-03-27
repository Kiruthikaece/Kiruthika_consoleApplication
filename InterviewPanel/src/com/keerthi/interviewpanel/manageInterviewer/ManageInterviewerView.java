package com.keerthi.interviewpanel.manageInterviewer;

import java.util.List;
import java.util.Scanner;

import com.keerthi.interviewpanel.model.Interviewer;

public class ManageInterviewerView {
    
    Scanner sc=new Scanner(System.in);
    
     ManageInterviewerModel manageInterviewerModel;


    public ManageInterviewerView()  {
        manageInterviewerModel=new ManageInterviewerModel(this);
    }


    public void addInterviewer() {
        Interviewer interviewer=new Interviewer();

        System.out.println("\nEnter Interviewer name:");
        interviewer.setName(sc.next());
        interviewer.setId(manageInterviewerModel.setInterviewerId()+1);
        System.out.println("\nEnter Interviewer username:");
        interviewer.setUserName(sc.next());
        System.out.println("\nEnter Interviewer email:");
        interviewer.setEmail(sc.next());
        System.out.println("\nEnter Interviewer Password:");
        interviewer.setPassword(sc.next());
        interviewer.setAvilable(true);
        manageInterviewerModel.ValidateInterviewer(interviewer);

    }


    public void onSuccess(Interviewer interviewer) {
       System.out.println("\n\n---- "+ interviewer.getName() +" added successfully   ----");
       checkAddNewInterviewer();
    }


    public void onExistAdmin(Interviewer interviewer) {
        System.out.println("\n\n---- "+ interviewer.getName() +" already exist  ----");
        checkAddNewInterviewer();
    }

    public void showAlert(String message) {
        System.out.println("\n"+message);
        checkAddNewInterviewer();
     }

    public void checkAddNewInterviewer() {
        System.out.println("\nDo you want to add more Interviewers? \nType Yes/No");
        String choice=sc.next();
        if(choice.equalsIgnoreCase("yes"))
        addInterviewer();
        else if(choice.equalsIgnoreCase("no"))
        System.out.println("\n Thanks for adding Interviewers");
        else {
            System.out.println("\n Invalid choice , Please Enter a valid choice ");
            checkAddNewInterviewer();
        }
        

    }


    public void listInterviewer() {
        manageInterviewerModel.getAllInterviewer();
    }


    public void printInterviewers(List<Interviewer> interviewerall) {
        String isAvilable;
        if(interviewerall.size()>0) {
            System.out.printf("%-15s %-20s %-20s %-20s\n","Interviewer Id","Interviewer Name","Email","IsAvilable");
            for(Interviewer inter:interviewerall) {
                if(inter.getAvilable())
                isAvilable="Avilable";
                else
                isAvilable="Not Avilable";
    
                System.out.printf("%-15d %-20s %-20s %-20s\n",inter.getId(),inter.getName(),inter.getEmail(),isAvilable);
            }
           
        }
        else
        System.out.println("No data avilable");
       
    }


    public void searchInterviewer() {
        System.out.println("Enter Interviewer name for search:");
        manageInterviewerModel.getSearchInterviewer(sc.next());
    }


    public void RemoveInterviewer() {
        System.out.println("Enter Interviewer name for deletion:");
        manageInterviewerModel.getRemoveInterviewer(sc.next());
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }
}
