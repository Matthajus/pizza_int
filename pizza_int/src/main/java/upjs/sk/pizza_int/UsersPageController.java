package upjs.sk.pizza_int;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;

public class UsersPageController {

	@FXML
	private ListView<String> usersListView;

	@FXML
	private MenuItem promoteMenuItem;

	@FXML
	private MenuItem demoteMenuItem;

	@FXML
	void initialize() {
		System.out.println("Prehľad userov otvorený!");
		
		usersListView.setItems(getAllUsers(DaoFactory.INSTANCE.getUserDao().getAll()));
		
	}
	
	public ObservableList<String> getAllUsers (List<User> userList){
		ObservableList<String> result = FXCollections.observableArrayList();
		for (User user : userList) {
			result.add(user.toString());
		}
		return result;
	}
}
