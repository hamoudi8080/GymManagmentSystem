package main.server.model.activities;
import main.server.databaseaccess.activities.ActivitiesDAOModel;
import main.shared.Activity;
import main.shared.UserName;

import java.util.ArrayList;

public class ActivitiesManager implements ActivitiesModel {

    private ActivitiesDAOModel activitiesDAO;

    public ActivitiesManager(ActivitiesDAOModel activitiesDAO){
        this.activitiesDAO = activitiesDAO;
    }

    @Override
    public ArrayList<Activity> requestActivities() {
        return activitiesDAO.requestActivities();
    }

    @Override
    public String deleteActivity(Activity activity) {
        return activitiesDAO.deleteActivity(activity);
    }

    @Override
    public String saveActivity(Activity activity) {
        return activitiesDAO.saveActivity(activity);
    }

    @Override
    public String registerActivities(Activity activity, UserName userName) {
        return activitiesDAO.registerActivities(activity, userName);
    }

    @Override
    public ArrayList<Activity> requestRegisteredActivities(UserName userName) {
        return activitiesDAO.requestRegisteredActivities(userName);
    }

    @Override
    public String cancelRegistration(Activity activity, UserName userName) {
        return activitiesDAO.cancelRegistration(activity,userName);
    }

}
