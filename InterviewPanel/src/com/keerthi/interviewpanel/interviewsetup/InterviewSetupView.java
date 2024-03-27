package com.keerthi.interviewpanel.interviewsetup;
import java.util.List;
import java.util.Scanner;

import com.keerthi.interviewpanel.model.Credential;
import com.keerthi.interviewpanel.model.InterviewProcess;

public class InterviewSetupView {

    Scanner sc=new Scanner(System.in);
    private InterviewSetupModel interviewSetupModel;

    public InterviewSetupView() {
        interviewSetupModel=new InterviewSetupModel(this);
    }

    public void init() {
     
       while(true) {
         System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println(" \n 1-Assign Interview\n 2-List Schedule\n 3-Go back\n");
         System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
         System.out.println("Enter your choice");
         int choice=sc.nextInt();
         switch(choice) {
            case 1:assignInterview();
                    break;
            case 2: scheduleList();
                    break;
            case 3: return;
         }
       }
    }

   
    private void assignInterview() {
       System.out.println("\nEnter Interviewer email");
       String interviewerEmail=sc.next();
       System.out.println("\nEnter Candiadte email");
       String candidateEmail=sc.next();
       interviewSetupModel.validateSchedule(interviewerEmail,candidateEmail);
    }

    private void scheduleList() {
      interviewSetupModel.getScheduleList();
    }

   public void ListSchedule(List<InterviewProcess> schedule) {
     if(schedule.size()>0) {
     interviewSetupModel.changeAvilability(schedule);
     System.out.printf("\n%-20s %-20s %-20s","Interviewer name","Candidate name","Status");
     for(InterviewProcess list:schedule)
     System.out.printf("\n%-20s  %-20s %-20s",list.getInterview().getName(),list.getcandidate().getCandidateName(),list.getStatus());
     }
     else {
      showAlert("No data avilable");
     }
    
     
   }

   public void showAlert(String message) {
      System.out.println("\n"+message);
   }

   public void updateStatus(Credential credentialInterViewer) {
      interviewSetupModel.FindCandidate(credentialInterViewer);
   }
   public void updateAppropriateResult(List<InterviewProcess> foundMatchCandidate) {
      if(foundMatchCandidate.size()==1) {
         for(InterviewProcess match:foundMatchCandidate) {
            if(!match.getcandidate().getStatus().equalsIgnoreCase("selected") && !match.getcandidate().getStatus().equalsIgnoreCase("Not selected")) {
               System.out.println("Enter the result for"+"\"" + match.getcandidate().getCandidateName() + "\""+"\n Type selected/Not selected");
               match.getcandidate().setStatus(sc.next());
               match.getInterview().setAvilable(true);
               match.setStatus("Interview completed");
               interviewSetupModel.finalUpdateAll();
            }
            else
            showAlert("You are already update result");      
         }
         
       }
       else
       showAlert("Currently You are not update result");
   }

}
