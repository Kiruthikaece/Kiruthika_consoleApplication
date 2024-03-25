package com.keerthi.interviewpanel.managecandidate;

import java.util.List;
import java.util.Scanner;

import com.keerthi.interviewpanel.model.Candidate;

public class CandidateView {

    Scanner sc=new Scanner(System.in);

    private CandidateModel candidateModel;

    public CandidateView() {
        candidateModel=new CandidateModel(this);
    }

    public void addCandidate() { 
        Candidate candidate=new Candidate();
        System.out.println("\nEnter candidate name:");
        candidate.setCandidateName(sc.next());
        candidate.setId();
        System.out.println("\nEnter candidate email:");
        candidate.setEmail(sc.next());
        candidate.setStatus("Interview not start");
        candidateModel.ValidateCandidate(candidate);
    }

    public void onSuccess(Candidate candidate) {
        System.out.println("\n\n---- "+ candidate.getCandidateName() +" added successfully   ----");
        checkAddNewCandidate();
    }

    public void existCandidate(Candidate candidate) {
        System.out.println("\n\n---- "+ candidate.getCandidateName() +" already exist  ----");
        checkAddNewCandidate();
    }


    public void checkAddNewCandidate() {
        System.out.println("\nDo you want to add more candidates? \nType Yes/No");
        String choice=sc.next();
        if(choice.equalsIgnoreCase("yes"))
        addCandidate();
        else if(choice.equalsIgnoreCase("no"))
        System.out.println("\n Thanks for adding candidate");
        else {
            System.out.println("\n Invalid choice , Please Enter a valid choice ");
            checkAddNewCandidate();
        }
    }

    public void showAlert(String message) {
        System.out.println("\n"+message);
        checkAddNewCandidate();
     }


     public void listCandidateAll() {
        candidateModel.getAllCandidate();
    }

    public void listCandidate(List<Candidate> list) {
        if (list.size() > 0) {
            System.out.printf("%-15s %-15s %-15s %-20s\n", "Candidate Id","Candidate Name", "Email", "Status");
            for (Candidate candy : list) {
                System.out.printf("%-15d %-15s %-15s %-20s\n",
                candy.getId(), candy.getCandidateName(),candy.getEmail(), candy.getStatus());
            }
        } else {
            System.out.println("No candidate available");
        }
    }

    public void searchCandidate() {
        System.out.println("Enter candidate name for search:");
        candidateModel.getSearchCandiadte(sc.next());
    }

    public void RemoveCandidate() {
        System.out.println("Enter candidate name for search");
        candidateModel.getRemoveCandidate(sc.next());
    }

    public void showMessage(String string) {
      System.out.println("\n"+string);
    }
    


    
    

    
}
