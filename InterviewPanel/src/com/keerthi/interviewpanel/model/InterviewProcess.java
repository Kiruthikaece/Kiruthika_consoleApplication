package com.keerthi.interviewpanel.model;

public class InterviewProcess {


    private Interviewer interview;
    private Candidate candidate;
    private String status;
    
    
   
    public Interviewer getInterview() {
        return interview;
    }
    public void setInterview(Interviewer interview) {
        this.interview = interview;
    }
    public Candidate getcandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
}
