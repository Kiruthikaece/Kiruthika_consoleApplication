package com.keerthi.interviewpanel.managecandidate;

import com.keerthi.interviewpanel.datalayer.InterviewDatabase;
import com.keerthi.interviewpanel.model.Candidate;

public class CandidateModel {

    private CandidateView candidateView;

    public CandidateModel(CandidateView candidateView) {
      this.candidateView=candidateView;
    }

    public void ValidateCandidate(Candidate candidate) {
      if(validateEmail(candidate.getEmail())) {
        if(InterviewDatabase.getInstance().insertCandidate(candidate))
        candidateView.onSuccess(candidate);
        else
        candidateView.existCandidate(candidate);
      }
      else
      candidateView.showAlert("Invalid Email");

    }

    private boolean validateEmail(String email) {
      String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
      return email.matches(regex);
    }

    public void getAllCandidate() {
      candidateView.listCandidate(InterviewDatabase.getInstance().getCandidateAll());
    }

    public void getSearchCandiadte(String string) {
      candidateView.listCandidate(InterviewDatabase.getInstance().searchQuery(string));
    }

    public void getRemoveCandidate(String name) {
        if(InterviewDatabase.getInstance().DeleteCandidateQuery(name))
        candidateView.showMessage("candidate removed succesfully");
        else
        candidateView.showMessage("No data avilable , please check");
    }
}
