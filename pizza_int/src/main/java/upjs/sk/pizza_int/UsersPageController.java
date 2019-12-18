package upjs.sk.pizza_int;

import java.util.ArrayList;

//zdoj na double klik v tableView: https://stackoverflow.com/questions/26563390/detect-doubleclick-on-row-of-tableview-javafx

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.User;

public class UsersPageController {

	private User selectedUser = new User();

	@FXML
	private TableView<User> usersTableView;

	@FXML
	private Button demoteButton;

	@FXML
	private Button promoteButton;

	@FXML
	void initialize() {
		System.out.println("Prehľad userov otvorený!");

		// vyplnanie usersTableView
		List<User> result = new ArrayList<User>();

		result = DaoFactory.INSTANCE.getUserDao().getAll();

		usersTableView.setItems(FXCollections.observableArrayList(result));

		TableColumn<User, Integer> roleCol = new TableColumn<User, Integer>("Role");
		roleCol.setCellValueFactory(new PropertyValueFactory<User, Integer>("Role"));
		roleCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(roleCol);

		TableColumn<User, String> isicCol = new TableColumn<User, String>("Isic");
		isicCol.setCellValueFactory(new PropertyValueFactory<User, String>("IsicCardNumber"));
		isicCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(isicCol);

		TableColumn<User, String> telCol = new TableColumn<User, String>("Tel_number");
		telCol.setCellValueFactory(new PropertyValueFactory<User, String>("Tel_number"));
		telCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(telCol);

		TableColumn<User, String> emailCol = new TableColumn<User, String>("Email");
		emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
		emailCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(emailCol);

		TableColumn<User, String> passwordCol = new TableColumn<User, String>("Password");
		passwordCol.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
		passwordCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(passwordCol);

		TableColumn<User, String> loginCol = new TableColumn<User, String>("Login");
		loginCol.setCellValueFactory(new PropertyValueFactory<User, String>("Login"));
		loginCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(loginCol);

		TableColumn<User, String> surnameCol = new TableColumn<User, String>("Surname");
		surnameCol.setCellValueFactory(new PropertyValueFactory<User, String>("Surname"));
		surnameCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(surnameCol);

		TableColumn<User, String> nameCol = new TableColumn<User, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("Name"));
		nameCol.setStyle("-fx-alignment: CENTER-RIGHT; -fx-font-weight: bold;");
		usersTableView.getColumns().add(nameCol);

		// dvojklikom si vyberieme usera
		usersTableView.setRowFactory(new Callback<TableView<User>, TableRow<User>>() {
			public TableRow<User> call(TableView<User> tv) {
				final TableRow<User> row = new TableRow<User>();
				row.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (event.getClickCount() == 2 && (!row.isEmpty())) {
							// event.MOUSE_CLICKED.
							selectedUser = DaoFactory.INSTANCE.getUserDao().getUserByLogin(row.getItem().getLogin(),
									row.getItem().getPassword());
							System.out.println("klikol som na riadok" + " " + selectedUser.toString());
							new Alert(Alert.AlertType.INFORMATION,
									"You select user: " + selectedUser.getName() + " " + selectedUser.getSurname())
											.showAndWait();
						}
					}
				});
				return row;
			}
		});

		// klik na promote button
		promoteButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("User bol promotnuty!");

				if (!selectedUser.getLogin().equals("admin")) {
					@SuppressWarnings("unused")
					User user = DaoFactory.INSTANCE.getUserDao().promoteUser(selectedUser);
				}
				MainAdminPageController.usersStage.getScene().getWindow().hide();

			}
		});

		// klik na demote button
		demoteButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("User bol demotnuty!");

				if (!selectedUser.getLogin().equals("admin")) {
					@SuppressWarnings("unused")
					User user = DaoFactory.INSTANCE.getUserDao().demoteUser(selectedUser);
				}
				MainAdminPageController.usersStage.getScene().getWindow().hide();

			}
		});

	}

	public ObservableList<String> getAllUsers(List<User> userList) {
		ObservableList<String> result = FXCollections.observableArrayList();
		for (User user : userList) {
			result.add(user.toString());
		}
		return result;
	}
}
