package main.client.model.personaltrainer;

import main.shared.PersonalTrainer;
import main.shared.UserName;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public interface PersonalTrainerModel {
    String savePersonalTrainer(PersonalTrainer personalTrainer);
    ArrayList<PersonalTrainer> getPersonalTrainers();
    String removePersonalTrainer(PersonalTrainer personalTrainer);
    void addListener(String eventName, PropertyChangeListener listener);

    String bookPersonalTrainer(PersonalTrainer personalTrainer, UserName userName);
}
