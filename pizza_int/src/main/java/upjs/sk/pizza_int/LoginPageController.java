package upjs.sk.pizza_int;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.User;

public class LoginPageController {
	
	public static User loggedUser;

	public static Stage regStage = new Stage();
	
	@FXML
	private TextField loginTextField;

	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private Button loginButton;

	@FXML
	private Button registerButton;

	@FXML
	void initialize() {
		System.out.println("LoginPage funguje!");
		
		// klik na login button
		loginButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("funguje to");
				getAccessData();
			}
		});
		
		// klik na register button
		registerButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("idem sa registrovat");
				openRegisterPage();
			}
		});

	}

	// metoda, ktora nacita hodnoty z text fieldov a zisti z databazy ci existuje taky user
	// na zaklade toho ci to je admin alebo uzivatel mu otvori prislusne okno
	private void getAccessData() {
		String login = loginTextField.getText();
		String password = passwordPasswordField.getText();

		User user = DaoFactory.INSTANCE.getUserDao().getUserByLogin(login, password);
		loggedUser = user;
		
		if (user == null) {
			System.out.println("User not found");
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Account Login");
			alert.setContentText("Wrong login data!");
			alert.setHeaderText("Invalid username or password.");
			alert.show();
		} else {
			App.stage.close();
			if (user.getRole() == 3) {
				openMainWindow();
			} else {
				openMainAdminPage();
			}

		}
	}

	// pomocna metoda pre otvorenie okna ked sa logujeme ako zakaznik
	private void openMainWindow() {
		MainWindowController controller = new MainWindowController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		fxmlLoader.setController(controller);
		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			App.stage.setScene(scene);
			App.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// pomocna metoda pre otvorenie okna ked sa logujeme ako admin
	private void openMainAdminPage() {
		MainAdminPageController controller = new MainAdminPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainAdminPage.fxml"));
		fxmlLoader.setController(controller);
		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			App.stage.setScene(scene);
			App.stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// pomocna metoda pre otvorenie okna, kde sa mozu ludia registrovat
	private void openRegisterPage() {
		RegisterPageController controller = new RegisterPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterPage.fxml"));
		fxmlLoader.setController(controller);
		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			regStage.setTitle("Registration");
			regStage.setScene(scene);
			regStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
