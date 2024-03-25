package com.keerthi.interviewpanel.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keerthi.interviewpanel.model.*;

public class InterviewDatabase {
    

    List<Interviewer> interviewerList=new ArrayList<>();
    List<Candidate> candidateList=new ArrayList<>();
    List<InterviewProcess> schedule=new ArrayList<>();

    private static InterviewDatabase interviewDatabase;
    
    ObjectMapper mapper = new ObjectMapper();

    public InterviewDatabase() {

    }

    public static InterviewDatabase getInstance() {
        if(interviewDatabase==null)
        interviewDatabase=new InterviewDatabase();
        
        return interviewDatabase;
    }

     public boolean insertAdmin(Interviewer admin) {
        boolean ispre=false;
        if(interviewerList.size()>0) {
            for(Interviewer existingAdmin:interviewerList)
            if(existingAdmin.getEmail().equals(admin.getName())) {
            ispre=true;
            break;
           } 
        }
        if(!ispre) {
            interviewerList.add(admin);
            try {
                File file = new File("E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\interviewer.json");
                if (!file.exists()) {
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<>());
                }
                List<Interviewer> existingInterviewers = mapper.readValue(file,
                        new TypeReference<List<Interviewer>>() {});
                existingInterviewers.add(admin);
                mapper.writeValue(file, existingInterviewers);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
     }

    public boolean insertCandidate(Candidate candidate) {
       boolean isNewCandidate=true;
        for(Candidate existCandidate:candidateList)
        if(existCandidate.getEmail().equals(candidate.getEmail())) {
            isNewCandidate=false;
            break;
       } 
      if(isNewCandidate) {
        candidateList.add(candidate);
         try {
                File file = new File("E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\candidate.json");
                if (!file.exists()) {
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<>());
                }
                List<Candidate> existingBooks = mapper.readValue(file,
                        new TypeReference<List<Candidate>>() {});
                existingBooks.add(candidate);
                mapper.writeValue(file, existingBooks);
    
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
       }
    return false;
    }


    public List<Candidate> searchQuery(String candidateName) {
        List<Candidate> searchCandidate=new ArrayList<>();
        if(candidateList.size()>0) {
            for(Candidate candiadte:candidateList)
            if(candiadte.getCandidateName().equals(candidateName))
            searchCandidate.add(candiadte);
        }
        return searchCandidate;
        
    }


    public List<Interviewer> searchInterviewQuery(String interviewerName) {
        List<Interviewer> searchInterviewer=new ArrayList<>();
        if(candidateList.size()>0) {
            for(Interviewer inter:interviewerList)
            if(inter.getName().equals(interviewerName))
            searchInterviewer.add(inter);
        }
        return searchInterviewer;
        
    }


    public List<Candidate> getCandidateAll() {
        try {
            candidateList = mapper.readValue(new File("E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\candidate.json"),
                    new TypeReference<List<Candidate>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
      return candidateList;
    }

    public boolean allocateInterview(String interviewerEmail, String candidateEmail) {

        InterviewProcess interviewprocess=new InterviewProcess();
        boolean isemail=false,iscandy=false;
        for(Interviewer inter:interviewerList)
        {
            if(inter.getEmail().equals(interviewerEmail) && inter.getAvilable()) 
            isemail=true;
            interviewprocess.setInterview(inter);
            break;
        }
        for(Candidate candy:candidateList)
        {
            if(candy.getEmail().equals(candidateEmail) && candy.getStatus().equals("Interview not start")) 
            iscandy=true;
            interviewprocess.setCandidate(candy);
            break;
        }
        if(isemail && iscandy) {
            interviewprocess.setStatus("Interview ongoing ");
            schedule.add(interviewprocess);
           return true;
        }
        else
        return false;
    }

    public List<InterviewProcess> getSchedule() {
      return schedule;
    }

    public boolean CheckValidLogin(Credential credential) {

         if(credential.getUserName().equals("admin") && credential.getPassword().equals("admin123"))
            return true;
         

         for(Interviewer interviewer:interviewerList) 
            if(credential.getUserName().equals(interviewer.getUserName()) && credential.getPassword().equals(interviewer.getPassword())) 
             return true;
    
             return false;

        }

    public boolean findAdminorInterviewer(Credential credential) {
        if(credential.getUserName().equals("admin") &&credential.getPassword().equals("admin123"))
        return true;

        return false;
    }

    public List<Interviewer> getInterviewerall() {
        try {
            interviewerList = mapper.readValue(new File("E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\interviewer.json"),
                    new TypeReference<List<Interviewer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interviewerList;
    }

    public List<InterviewProcess> foundMatchCandidate(Credential credentialInterViewer) {
        List<InterviewProcess> getCandiadte=new ArrayList<>();
        for(InterviewProcess oneschedule:schedule)
        if(credentialInterViewer.getUserName().equals(oneschedule.getInterview().getUserName())) {
            getCandiadte.add(oneschedule);
        }
      
        return getCandiadte;
    }

    public boolean DeleteCandidateQuery(String name) {
        Iterator<Candidate> iterator = candidateList.iterator();
        boolean isRemoved = false;
    
        while(iterator.hasNext()) {
            Candidate candidate = iterator.next();
            if(candidate.getCandidateName().equalsIgnoreCase(name)) {
                iterator.remove(); 
                isRemoved = true;
                break;
            }
        }
    
        if (isRemoved) {
            try { 
                mapper.writeValue(new File("E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\candidate.json"), candidateList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return isRemoved;
    }

    public boolean DeleteInterviewerQuery(String name) {
        Iterator<Interviewer> iterator = interviewerList.iterator();
        boolean isRemoved = false;
    
        while(iterator.hasNext()) {
            Interviewer interviewer = iterator.next();
            if(interviewer.getName().equalsIgnoreCase(name)) {
                iterator.remove(); 
                isRemoved = true;
                break;
            }
        }
    
        if (isRemoved) {
            try { 
                mapper.writeValue(new File("E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\interviewer.json"), interviewerList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return isRemoved;
    }


}