package upjs.sk.pizza_int;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegisterPageController {

	@FXML
	private TextField FirstNameTextField;

	@FXML
	private TextField LastNameTextField;

	@FXML
	private TextField AccountTextField;

	@FXML
	private TextField PasswordTextField;

	@FXML
	private TextField EmailAddressTextField;

	@FXML
	private TextField IsicCardNumberTextField;

	@FXML
	private TextField TelephoneNumberTextField;

	@FXML
	private Button RegisterButton;

	@FXML
	void initialize() {
		System.out.println("registracia funguje");
		
		RegisterButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("funguje to");
				LoginPageController.regStage.getScene().getWindow().hide();
			}
		});

	}

	}
	
