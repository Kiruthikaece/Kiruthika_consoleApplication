package com.keerthi.interviewpanel.model;

public class Candidate {
    private String candidateName;
    private int id;
    private String email;
    private String status;

    public String getCandidateName() {
        return candidateName;
    }
    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }   
    public int getId() {
        return id;
    }
    public void setId() {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String result) {
        this.status = result;
    }
}
