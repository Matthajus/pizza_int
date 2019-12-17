package upjs.sk.pizza_int;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.Pizza;

public class EditPizzaPageController {

	@FXML
	private TextField pizzaNameTextField;

	@FXML
	private TextField descriptionTextField;

	@FXML
	private TextField weightTextField;

	@FXML
	private TextField priceTextField;

	@FXML
	private Button editButton;

	@FXML
	private Button backButton;

	@FXML
	private ComboBox<String> pizzaComboBox;

	@FXML
	void initialize() {
		
		List<Pizza> pizzaList = DaoFactory.INSTANCE.getPizzaDao().getAll();
		int counter = 0;
		for (Pizza pizza : pizzaList) {
			pizzaComboBox.getItems().add(counter, pizza.getName());
			counter++;
		}
		
		
		// klik na back button
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Späť na hlavnu tránku admina!");
				openMainAdminPage();
			}
		});
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
}