package main.client.view.staff.clients.list;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import main.client.model.clients.ClientModel;
import main.shared.TheClient;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class ClientsListViewModel
{
  private ClientModel clientManager;
  private ObservableList<TheClient> list;

  // todo fix this constructor problem
  public ClientsListViewModel(ClientModel clientManager)
  {
    System.out.println("Constructor isnt working");

    this.clientManager = clientManager;
    list = FXCollections.observableArrayList();
    populateList();

    clientManager.addListener("Client Added", evt -> clientAdded(evt));
    clientManager.addListener("Client Removed", evt -> clientRemoved(evt));

    System.out.println("Constructor is working");
  }

  private void clientAdded(PropertyChangeEvent evt)
  {
    TheClient clientAdded = (TheClient) evt.getNewValue();
    list.add(clientAdded);
    System.out.println(clientAdded);
    System.out.println("Client added view model");
  }

  private void clientRemoved(PropertyChangeEvent evt)
  {
    TheClient clientRemoved = (TheClient) evt.getNewValue();
    list.add(clientRemoved);
    System.out.println(clientRemoved);
  }

  public void populateList()
  {
    list.addAll(getClients());
  }

  public ArrayList<TheClient> getClients()
  {
    ArrayList<TheClient> test = clientManager.getClients();
    if (test==null)
    {
      test = new ArrayList<>();
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("ERROR");
      alert.setContentText("Connection Error. Please restart the program");
      alert.showAndWait();
      return test;
    }
    return test;
  }

  public void removeClient(TheClient theClient)
  {
    String response = clientManager.removeClient(theClient);

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Remove operation");
    alert.setContentText(response);
    alert.showAndWait();
  }

  public ObservableList<TheClient> getList()
  {
    return list;
  }
}