package com.keerthi.railway.trainmanagement;

import java.util.List;

import com.keerthi.railway.datalayer.FileManager;
import com.keerthi.railway.model.Train;

class TrainManagementModel {

private TrainManagementView trainManagementView;
   
  public TrainManagementModel(TrainManagementView trainManagementView) {
        this.trainManagementView=trainManagementView;
    }

public void validateTrain(Train train) {
    if(FileManager.getInstance().addTrainInFile(train))
    trainManagementView.showMessage(train.getTrainName()+" added successfully");
}

public void getAll() {
   List<Train> list= FileManager.getInstance().getAllTrain();
   if(list.size()>0)
   trainManagementView.showTrain(list);
   else
   trainManagementView.showMessage("Train not exist");

}


}