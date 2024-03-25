package com.keerthi.interviewpanel.interviewsetup;

import java.util.List;

import com.keerthi.interviewpanel.datalayer.InterviewDatabase;
import com.keerthi.interviewpanel.model.Credential;
import com.keerthi.interviewpanel.model.InterviewProcess;

public class InterviewSetupModel {

    private InterviewSetupView InterviewSetupView;

    public InterviewSetupModel(InterviewSetupView interviewSetupView) {
        this.InterviewSetupView = interviewSetupView;
    }

    public void validateSchedule(String interviewerEmail, String candidateEmail) {
         if(InterviewDatabase.getInstance().allocateInterview(interviewerEmail,candidateEmail)) {    
            InterviewSetupView.showAlert("Interview Assigned sucessfully,  Interview start....");
        }  
         else
         InterviewSetupView.showAlert("No data avilable");
    }

    public void changeAvilability(List<InterviewProcess> schedule) {
        for(InterviewProcess list:schedule) {
            list.getInterview().setAvilable(false);
            list.getcandidate().setStatus("Interview on going");
        }
    }

    public void getScheduleList() {
        InterviewSetupView.ListSchedule(InterviewDatabase.getInstance().getSchedule());
    }

    public void FindCandidate(Credential credentialInterViewer) {
        InterviewSetupView.updateAppropriateResult(InterviewDatabase.getInstance().foundMatchCandidate(credentialInterViewer));
    }
    
}
