package main.client.view.staff.personaltrainers.list;

import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import main.client.model.personaltrainer.PersonalTrainerModel;
import main.shared.PersonalTrainer;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class PersonalTrainersListViewModel
{
  private PersonalTrainerModel personalTrainerManager;
  private String personalTrainer;
  private SimpleListProperty personalTrainersList;
  private ObservableList<String> list = FXCollections.observableArrayList();

  public PersonalTrainersListViewModel(PersonalTrainerModel personalTrainerManager)
  {
    this.personalTrainerManager = personalTrainerManager;
    personalTrainersList = new SimpleListProperty();
    personalTrainerManager.addListener("Personal Trainer Added", evt -> personalTrainerAdded(evt));
    personalTrainerManager.addListener("Personal Trainer Removed", evt -> personalTrainerRemoved(evt));
  }

  private void personalTrainerRemoved(PropertyChangeEvent evt) {
    PersonalTrainer personalTrainerRemoved = (PersonalTrainer) evt.getNewValue();
    Platform.runLater(()->list.remove(personalTrainerRemoved.toString()));
    personalTrainersList.setValue(list);
  }

  private void personalTrainerAdded(PropertyChangeEvent evt) {
    PersonalTrainer personalTrainerAdded = (PersonalTrainer) evt.getNewValue();
    list.add(personalTrainerAdded.toString());
    personalTrainersList.setValue(list);

  }

  public void populateList()
  {
    ArrayList<PersonalTrainer> test = getPersonalTrainers();
    for (PersonalTrainer personalTrainer : test)
    {
      list.add(personalTrainer.toString());
    }
    personalTrainersList.setValue(list);
  }

  public ArrayList<PersonalTrainer> getPersonalTrainers()
  {
    ArrayList<PersonalTrainer> test = personalTrainerManager.getPersonalTrainers();
    if(test==null){
      test = new ArrayList<>();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("Connection Error");
      alert.showAndWait();
      return test;
    }else{
      return test;
    }
  }

  public void removePersonalTrainer(PersonalTrainer personalTrainer)
  {
    String response = personalTrainerManager.removePersonalTrainer(personalTrainer);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Remove operation");
    alert.setContentText(response);
    alert.showAndWait();
  }

  public SimpleListProperty getPersonalTrainersList()
  {
    return personalTrainersList;
  }

}
