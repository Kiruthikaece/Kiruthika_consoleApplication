package com.keerthi.interviewpanel.manageInterviewer;
import com.keerthi.interviewpanel.datalayer.InterviewDatabase;
import com.keerthi.interviewpanel.model.Interviewer;

public class ManageInterviewerModel {

    private ManageInterviewerView manageInterviewerView;


    public ManageInterviewerModel(ManageInterviewerView manageInterviewerView) {
        this.manageInterviewerView=manageInterviewerView;
    }

    public void ValidateInterviewer(Interviewer admin) {

    if(validateEmail(admin.getEmail())) {
       if(InterviewDatabase.getInstance().insertInterviewer(admin))
       manageInterviewerView.onSuccess(admin);
       else
       manageInterviewerView.onExistAdmin(admin);
    }
    else
    manageInterviewerView.showAlert("Invalid Email");
    }

    private boolean validateEmail(String email) {
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(regex);
      }
  
    public void getAllInterviewer() {
        manageInterviewerView.printInterviewers(InterviewDatabase.getInstance().getInterviewerall());
    }

    public void getSearchInterviewer(String string) {
        manageInterviewerView.printInterviewers(InterviewDatabase.getInstance().searchInterviewQuery(string));
    }

    public void getRemoveInterviewer(String name) {
        if(InterviewDatabase.getInstance().DeleteInterviewerQuery(name))
        manageInterviewerView.showMessage("candidate removed succesfully");
        else
        manageInterviewerView.showMessage("No data avilable , please check");
    }
    
    public int setInterviewerId() {
            if(InterviewDatabase.getInstance().counterInterview()>0)
             return InterviewDatabase.getInstance().counterInterview();
            else
            return 0;
 
}

}
