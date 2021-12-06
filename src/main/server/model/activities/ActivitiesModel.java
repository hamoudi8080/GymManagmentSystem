package main.server.model.activities;
import main.shared.Activity;
import java.util.ArrayList;

public interface ActivitiesModel {
    ArrayList<Activity> requestActivities();
    String deleteActivity(Activity activity);
    String saveActivity(Activity activity);
}
