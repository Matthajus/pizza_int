package upjs.sk.pizza_int;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

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

				User regingUser = new User();
				regingUser.setName(FirstNameTextField.getText());
				regingUser.setSurname(LastNameTextField.getText());
				regingUser.setLogin(AccountTextField.getText());
				regingUser.setPassword(PasswordTextField.getText());
				regingUser.setEmail(EmailAddressTextField.getText());
				regingUser.setIsicCardNumber(IsicCardNumberTextField.getText());
				regingUser.setTel_number(TelephoneNumberTextField.getText());
				
				User user = DaoFactory.INSTANCE.getUserDao().saveUser(regingUser);
				
				if (user == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Registration");
					alert.setContentText("Wrong registration data!");
					alert.setHeaderText("Please, fill the registration data to finish registration!");
					alert.show();
				} else {
					LoginPageController.regStage.getScene().getWindow().hide();
				}

				

			}
		});

	}

}
