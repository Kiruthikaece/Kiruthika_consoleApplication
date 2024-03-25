package com.keerthi.interviewpanel.manageInterviewer;
import com.keerthi.interviewpanel.datalayer.InterviewDatabase;
import com.keerthi.interviewpanel.model.Interviewer;

public class ManageInterviewerModel {

    private ManageInterviewerView manageInterviewerView;


    public ManageInterviewerModel(ManageInterviewerView manageInterviewerView) {
        this.manageInterviewerView=manageInterviewerView;
    }

    public void ValidateInterviewer(Interviewer admin) {
       if(InterviewDatabase.getInstance().insertAdmin(admin))
       manageInterviewerView.onSuccess(admin);
       else
       manageInterviewerView.onExistAdmin(admin);
    }

    public void getAllInterviewer() {
        manageInterviewerView.printInterviewers(InterviewDatabase.getInstance().getInterviewerall());
    }

    public void getSearchInterviewer(String string) {
        manageInterviewerView.printInterviewers(InterviewDatabase.getInstance().searchInterviewQuery(string));
    }

    public void getRemoveInterviewer(String name) {
        if(InterviewDatabase.getInstance().DeleteInterviewerQuery(name))
        manageInterviewerView.showAlert("candidate removed succesfully");
        else
        manageInterviewerView.showAlert("No data avilable , please check");
    }
    
}
