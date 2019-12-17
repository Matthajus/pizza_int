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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.Pizza;

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
    private Button backButton;

	@FXML
	void initialize() {
		System.out.println("Pridávanie pizze sa spojazdnilo!");

		addButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("funguje to");

				Pizza addPizza = new Pizza();
				addPizza.setName(pizzaNameTextField.getText());
				addPizza.setDescription(descriptionTextField.getText());
				if (weightTextField.getLength() != 0) {
					addPizza.setWeight(Integer.parseInt(weightTextField.getText()));
				} else {
					addPizza.setWeight(0);
				}
				if (priceTextField.getLength() != 0) {
					addPizza.setPrice(Double.parseDouble(priceTextField.getText()));
				} else {
					addPizza.setPrice(0);
				}
				
				Pizza pizza = DaoFactory.INSTANCE.getPizzaDao().savePizza(addPizza);

				if (pizza == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Add pizza");
					alert.setContentText("Please, fill the pizza data to finish adding pizza!");
					alert.setHeaderText("Wrong pizza data!");
					alert.show();
				} else {
					openMainAdminPage();
					
				}

			}
		});
		
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