package upjs.sk.pizza_int;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;

public class MainWindowController {

	@FXML
	private TableView<?> pizzaListTableView;

	@FXML
	private Button orderButton;

	@FXML
	void initialize() {
		System.out.println("Hlavna strana zakaznika funguje");

	}
}
