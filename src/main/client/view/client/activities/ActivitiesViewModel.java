package main.client.view.client.activities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.client.model.activities.ActivitiesModel;
import main.client.model.login.LoginModel;
import main.client.view.client.viewregisteredlist.RegisteredListViewModel;
import main.shared.Activity;
import main.shared.UserName;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;


public class ActivitiesViewModel {


    private ActivitiesModel activitiesManager;
    private LoginModel loginManager;
    private UserName userName;
    private ObservableList<Activity> items;
    private RegisteredListViewModel registeredListViewModel;

    public ActivitiesViewModel(ActivitiesModel activitiesManager, LoginModel loginManager){
        this.activitiesManager = activitiesManager;
        this.loginManager = loginManager;
        userName = loginManager.getUserName();

        items = FXCollections.observableArrayList();
        activitiesManager.addListener("Activity Deleted", evt -> activityDeleted(evt));
        activitiesManager.addListener("Activity Added", evt -> activityAdded(evt));

        loadActivities();
    }

    private void activityAdded(PropertyChangeEvent evt) {
        Activity activityAdded = (Activity) evt.getNewValue();
        items.add(activityAdded);
    }

    private void activityDeleted(PropertyChangeEvent evt) {
        Activity activityDeleted = (Activity) evt.getNewValue();
        items.remove(activityDeleted);
    }

    public ArrayList<Activity> requestActivities() {
        return  activitiesManager.requestActivities();
    }


    public ObservableList<Activity> getItemsList() {
        return items;
    }


    public void registerActivity(Activity activity){

        activitiesManager.registerActivities(activity,userName);
        System.out.println(userName.getUserName() + activity.getActivityName());
    }
    public void loadActivities() {
        items.addAll(activitiesManager.requestActivities());
    }



}


