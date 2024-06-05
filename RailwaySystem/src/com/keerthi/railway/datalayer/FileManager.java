package com.keerthi.railway.datalayer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keerthi.railway.model.Credential;
import com.keerthi.railway.model.Passanger;
import com.keerthi.railway.model.Train;

public class FileManager {
    
    private static FileManager fileManager;

    ObjectMapper mapper = new ObjectMapper();

    String userJson="E:\\ConsoleApplication\\RailwaySystem\\src\\com\\keerthi\\railway\\json\\user.json";
    String trainJson="E:\\ConsoleApplication\\RailwaySystem\\src\\com\\keerthi\\railway\\json\\train.json";
    String passangerJSon="E:\\ConsoleApplication\\RailwaySystem\\src\\com\\keerthi\\railway\\json\\passanger.json";

    List<Train> trainList=new ArrayList<>();
    List<Credential> userList=new ArrayList<>();
    List<Passanger> passangerList=new ArrayList<>();

    public static FileManager getInstance() {
        if(fileManager==null)
        fileManager=new FileManager();
       
        return fileManager;
    }


    public boolean checkLogin(Credential credential) {
        boolean isUser=false;
        for(Credential existUser:userList)
        if(existUser.getUserName().equals(credential.getUserName())) {
            isUser=true;
             break;
         }
         if(isUser) 
            return true; 
         else 
            return false;
         
         
    }

    public boolean checkSignUp(Credential credential) {
        boolean isNewUser=true;
         for(Credential existUser:userList)
         if(existUser.getUserName().equals(credential.getUserName())) {
            isNewUser=false;
           break;
         }
         if(isNewUser) {
            userList.add(credential);
            AddUserJson(credential);
            return true;
         }
         else
         return false;   
    }

   


    public boolean addTrainInFile(Train train) {
         boolean isNewTrain=true;
         for(Train existtrain:trainList)
         if(existtrain.getTrainNo()==train.getTrainNo()) {
          isNewTrain=false;
           break;
         }
         if(isNewTrain) {
            trainList.add(train);
            AddTrainJson(train);
            return true;
         }
         else
         return false;   
    }


    public List<Train> getAllTrain() {
        return trainList;
    }

    public List<Passanger> getAllPassanger() {
        return passangerList;
    }


    public boolean addPassanger(Passanger passanger) {
       boolean isAvilable=false;
       for(Train existtrain:trainList) {
        if(existtrain.getTrainNo()==passanger.getTrainNo()) {
            if(existtrain.getSeats()>0) {
                passanger.setSeat_no(existtrain.getSeats());
                existtrain.setSeats(existtrain.getSeats()-1);
                updateSeatNo();
                isAvilable=true;
            }  
           }
          }
       if(isAvilable) {      
            passanger.setId(passangerList.size()+1);
            passanger.setStatus("CNF");
            passangerList.add(passanger);
            addPassangerJson(passanger);
            return true;
       }
       return false;
    }



    private void updateSeatNo() {
        try {
            mapper.writeValue(new File(trainJson), trainList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Train> getTrain(String fromStation, String toStation) {
        List<Train> availableTrains = new ArrayList<>();
        
        for (Train train : trainList) {
            String[] routes = train.getRoutes();
            boolean fromStationFound = false;
            boolean toStationFound = false;
            for (String route : routes) {
                if (route.equals(fromStation)) {
                    fromStationFound = true;
                }
                if (route.equals(toStation)) {
                    toStationFound = true;
                }
                if (fromStationFound && toStationFound) {
                    availableTrains.add(train);
                    break;
                }
            }
        }
        
        return availableTrains;
    }
    

    public List<Passanger> getPnrPassanger(int pnr) {
        List<Passanger> pnrPassanger=new ArrayList<>();
         
        for(Passanger passanger:passangerList) {
          if(pnr==passanger.getPnr())
          pnrPassanger.add(passanger);
         }

         return pnrPassanger;
    }
    
    public void AddUserJson(Credential credential) {
        try {
            File file = new File(userJson);
            if (!file.exists()) {
                file.createNewFile();
                mapper.writeValue(file, new ArrayList<>());
            }
            List<Credential> existingCandidate = mapper.readValue(file,
                    new TypeReference<List<Credential>>() {});
                    existingCandidate.add(credential);
            mapper.writeValue(file, existingCandidate);
        } catch (IOException e) {
            e.printStackTrace();
         }
}

public void AddTrainJson(Train train) {
    try {
        File file = new File(trainJson);
        if (!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<>());
        }
        List<Train> existingTrain = mapper.readValue(file,
                new TypeReference<List<Train>>() {});
                existingTrain.add(train);
        mapper.writeValue(file, existingTrain);
    } catch (IOException e) {
        e.printStackTrace();
     }
}

public void addPassangerJson(Passanger passanger) {
    try {
        File file = new File(passangerJSon);
        if (!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<>());
        }
        List<Passanger> existPassanger = mapper.readValue(file,
                new TypeReference<List<Passanger>>() {});
                existPassanger.add(passanger);
        mapper.writeValue(file, existPassanger);
    } catch (IOException e) {
        e.printStackTrace();
     }
}

public List<Credential> retrieveCandiadteJson() {
    try {
        File file = new File(userJson);
        if(!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<>());
        }
        userList = mapper.readValue(new File(userJson),
                new TypeReference<List<Credential>>() {});
    } catch (IOException e) {
        e.printStackTrace();
    }
    return userList;

}

public List<Train> retrieveTrainJson() {
    try {
        File file = new File(trainJson);
        if(!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<>());
        }
        trainList = mapper.readValue(new File(trainJson),
                new TypeReference<List<Train>>() {});
    } catch (IOException e) {
        e.printStackTrace();
    }
    return trainList;

}

public List<Passanger> retrievePassangerJson() {
    try {
        File file = new File(passangerJSon);
        if(!file.exists()) {
            file.createNewFile();
            mapper.writeValue(file, new ArrayList<>());
        }
        passangerList = mapper.readValue(new File(passangerJSon),
                new TypeReference<List<Passanger>>() {});
    } catch (IOException e) {
        e.printStackTrace();
    }
    return passangerList;

}
    public void retrieveAll() {
        retrieveCandiadteJson();
        retrieveTrainJson();
        retrievePassangerJson();
    }


    public List<Passanger> isPresentPnr(int pnr) {
        List<Passanger> pnrPassanger=new ArrayList<>();
         for(Passanger passanger:passangerList)
         if(passanger.getPnr()==pnr)
         pnrPassanger.add(passanger);

         return pnrPassanger;

    }


    public void removePassanger(List<Passanger> pnrPassanger) {
        Iterator<Passanger> iterator = passangerList.iterator();
        while (iterator.hasNext()) {
            Passanger currentPassenger = iterator.next();
            for (Passanger pnrPass : pnrPassanger) {
                if (currentPassenger.getPnr() == pnrPass.getPnr()) {
                    iterator.remove();
                    break;
                }
            }
        }
        updatePassanger();
    }


 public void updatePassanger() {
        try {
            mapper.writeValue(new File(passangerJSon), passangerList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


public List<Passanger> avilablePassanger(int passangerId) {
   List<Passanger> searchPassanger=new ArrayList<>();
   for(Passanger passanger:passangerList) {
    if(passanger.getId()==passangerId)
    searchPassanger.add(passanger);
   }
   return searchPassanger;

}


public boolean changePassangerStatus(int pnr, int status) {
   
    System.out.println(pnr+"pnr"+status+"status");
    boolean isPre=false;
    for(Passanger passanger:passangerList) {
        if(passanger.getPnr()==pnr) {
            if(status==2) 
            passanger.setStatus("cancel");
            isPre=true;
         }
    }
    updatePassanger();
    return isPre;
}
   
}
