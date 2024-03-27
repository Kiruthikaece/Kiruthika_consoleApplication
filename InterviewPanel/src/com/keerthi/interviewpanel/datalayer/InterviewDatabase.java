package com.keerthi.interviewpanel.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keerthi.interviewpanel.model.*;

public class InterviewDatabase {
    

    String interviewerJsonPath="E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\interviewer.json";
    String candidateJsonPath="E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\candidate.json";
    String scheduleJsonPath="E:\\ConsoleApplication\\InterviewPanel\\src\\com\\keerthi\\interviewpanel\\json_folder\\schedule.json";
   
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

     public boolean insertInterviewer(Interviewer interviewer) {
        boolean ispre=false;
        if(interviewerList.size()>0) {
            for(Interviewer existingAdmin:interviewerList)
            if(existingAdmin.getEmail().equals(interviewer.getEmail())) {
            ispre=true;
            break;
           } 
        }
        if(!ispre) {
            interviewerList.add(interviewer);
            AddInterviewerToJson(interviewer);
            return true;
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
        AddCandidiateToJson(candidate);
        return true;
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
      return candidateList;
    }

    public String showMessage(String message) {
        return message;
    }
    public boolean allocateInterview(String interviewerEmail, String candidateEmail) {

        InterviewProcess interviewprocess=new InterviewProcess();
        boolean isemail=false,iscandy=false;
        for(Interviewer inter:interviewerList)
        {
            if(inter.getEmail().equals(interviewerEmail))  {
                if(inter.getAvilable()) {
                    isemail=true;
                    interviewprocess.setInterview(inter);
                    break;
                }
                else
                showMessage("Interviewer not avilable");
            }
            
        }
        for(Candidate candy:candidateList)
        {
            if(candy.getEmail().equals(candidateEmail))
            {
                if(candy.getStatus().equals("Interview not start"))  {
                    iscandy=true;
                    interviewprocess.setCandidate(candy);
                    break;
                }
                else
                showMessage("Candidate already attend interview");
            } 
           
        }
        if(isemail && iscandy) {
            interviewprocess.setStatus("Interview ongoing ");
            schedule.add(interviewprocess);
            AddScheduleToJson(interviewprocess);
            showMessage("Interview Assigned sucessfully,  Interview start....");
           return true;
        }
        else {
            showMessage("No data avilable");
            return false;
        }
       
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

public void AddInterviewerToJson(Interviewer interviewer) {
    try {
        File file = new File(interviewerJsonPath);
        if (!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<>());
        }
        List<Interviewer> existingInterviewers = mapper.readValue(file,
                new TypeReference<List<Interviewer>>() {});
        existingInterviewers.add(interviewer);
        mapper.writeValue(file, existingInterviewers);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public List<Interviewer> retrieveInterviewerJson() {
    try {
            interviewerList = mapper.readValue(new File(interviewerJsonPath),
                    new TypeReference<List<Interviewer>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return interviewerList;
}

public void AddCandidiateToJson(Candidate candidate) {
 try {
                File file = new File(candidateJsonPath);
                if (!file.exists()) {
                    file.createNewFile();
                    mapper.writeValue(file, new ArrayList<>());
                }
                List<Candidate> existingCandidate = mapper.readValue(file,
                        new TypeReference<List<Candidate>>() {});
                        existingCandidate.add(candidate);
                mapper.writeValue(file, existingCandidate);
            } catch (IOException e) {
                e.printStackTrace();
            }
  }
  
public List<Candidate> retrieveCandiadteJson() {
 try {
            candidateList = mapper.readValue(new File(candidateJsonPath),
                    new TypeReference<List<Candidate>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return candidateList;
}

public void getAllData() {
    retrieveCandiadteJson();
    retrieveInterviewerJson();
    retrieveScheduleJson();
}


public void AddScheduleToJson(InterviewProcess schedule) {
    try {
                   File file = new File(scheduleJsonPath);
                   if (!file.exists()) {
                       file.createNewFile();
                       mapper.writeValue(file, new ArrayList<>());
                   }
                   List<InterviewProcess> existingSchedule = mapper.readValue(file,
                           new TypeReference<List<InterviewProcess>>() {});
                    existingSchedule.add(schedule);
                   mapper.writeValue(file, existingSchedule);
               } catch (IOException e) {
                   e.printStackTrace();
               }
     }
public List<InterviewProcess> retrieveScheduleJson() {
        try {
            schedule = mapper.readValue(new File(scheduleJsonPath),
                           new TypeReference<List<InterviewProcess>>() {});
               } catch (IOException e) {
                   e.printStackTrace();
               }
               return schedule;
       }

       public void updateInterviewersToJson() {
        try {
            System.out.println("hello");
            mapper.writeValue(new File(interviewerJsonPath), interviewerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void updateCandidatesToJson() {
        try {
            mapper.writeValue(new File(candidateJsonPath), candidateList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void updateInterviewProcessToJson() {
        try {
            mapper.writeValue(new File(scheduleJsonPath), schedule);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateAllJson() {
        updateCandidatesToJson();
        updateCandidatesToJson();
        updateInterviewProcessToJson();

    }

    public int counterBook() {
        int size=0;
        try {
            File jsonFile = new File(candidateJsonPath);
            if(jsonFile.exists()) {
            List<Object> jsonList = mapper.readValue(jsonFile, new TypeReference<List<Object>>() {});
             size = jsonList.size();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }

    public int counterInterview() {
        int size=0;
        try {
            File jsonFile = new File(interviewerJsonPath);
            if(jsonFile.exists()) {
            List<Object> jsonList = mapper.readValue(jsonFile, new TypeReference<List<Object>>() {});
             size = jsonList.size();
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return size;
    }
   
} 