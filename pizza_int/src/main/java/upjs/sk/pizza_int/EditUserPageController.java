package upjs.sk.pizza_int;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.User;
import upjs.sk.pizza_int.Models.UserFxModel;

public class EditUserPageController {

	public static User selectedUser = null;
	private UserFxModel fxModel = new UserFxModel();

	@FXML
	private Button editButton;

	@FXML
	private Button backButton;

	@FXML
	private ComboBox<String> userComboBox;

	@FXML
	private TextField userNameTextField;

	@FXML
	private TextField userSurameTextField;

	@FXML
	private TextField loginTextField;

	@FXML
	private TextField passwordTextField;

	@FXML
	private TextField emailTextField;

	@FXML
	private TextField telNumberTextField;

	@FXML
	private TextField isicCardNumberTextField;

	@FXML
	void initialize() {
		System.out.println("Edit usera!");

		userNameTextField.textProperty().bindBidirectional(fxModel.nameProperty());
		userSurameTextField.textProperty().bindBidirectional(fxModel.surnameProperty());
		loginTextField.textProperty().bindBidirectional(fxModel.loginProperty());
		passwordTextField.textProperty().bindBidirectional(fxModel.passwordProperty());
		emailTextField.textProperty().bindBidirectional(fxModel.emailProperty());
		telNumberTextField.textProperty().bindBidirectional(fxModel.telNumberProperty());
		isicCardNumberTextField.textProperty().bindBidirectional(fxModel.isicCardNumberProperty());

		List<User> userList = DaoFactory.INSTANCE.getUserDao().getAll();
		int counter = 0;
		for (User user : userList) {
			userComboBox.getItems().add(counter, user.getName() + " " + user.getSurname());
			counter++;
		}

		userComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				String user = userComboBox.getSelectionModel().getSelectedItem();
				// ziskanie mena danej osoby
				String arr[] = user.split(" ", 2);

				String name = arr[0];

				selectedUser = DaoFactory.INSTANCE.getUserDao().getUserByName(name);
				fxModel.setUser(selectedUser);
			}
		});

		// klik na edit button
		editButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Editujem!");

				@SuppressWarnings("unused")
				User user = DaoFactory.INSTANCE.getUserDao().editUser(fxModel.getUser());
				MainAdminPageController.editUserStage.getScene().getWindow().hide();

			}
		});

		// klik na back button
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Späť na hlavnu stránku admina!");
				MainAdminPageController.editUserStage.getScene().getWindow().hide();
			}
		});

	}
}