package upjs.sk.pizza_int;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddPageController {

	@FXML
	private TextField pizzaNameTextField;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private TextField weightTextField;

	@FXML
	private TextField priceTextField;

	@FXML
	private Button addButton;

	@FXML
	void initialize() {
		System.out.println("Pridávanie pizze sa spojazdnilo!");
	}
}