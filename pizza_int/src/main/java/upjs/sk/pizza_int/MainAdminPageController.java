package upjs.sk.pizza_int;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;

public class MainAdminPageController {

	@FXML
	private TableView<?> pizzaListTableView;

	@FXML
	private Button orderButton;

	@FXML
	private MenuBar upperMenuBar;

	@FXML
	private Menu pizzaListMenu;

	@FXML
	private MenuItem addEditPizzaMenuItem;

	@FXML
	private Menu userListMenu;

	@FXML
	private MenuItem usersMenuItem;

	@FXML
	private Menu historyMenu;

	@FXML
	private MenuItem historyMenuItem;

	@FXML
	void initialize() {
		System.out.println("Hlavna strana pre admina funguje");
	}
}
