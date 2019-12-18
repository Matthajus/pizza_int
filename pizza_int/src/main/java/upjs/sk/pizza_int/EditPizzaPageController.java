package upjs.sk.pizza_int;

import java.io.IOException;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	public static Pizza selectedPizza = null;

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
		System.out.println("Edit pizze");

		List<Pizza> pizzaList = DaoFactory.INSTANCE.getPizzaDao().getAll();
		int counter = 0;
		for (Pizza pizza : pizzaList) {
			pizzaComboBox.getItems().add(counter, pizza.getName() + "");
			counter++;
		}

		pizzaComboBox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				for (Pizza pizza : pizzaList) {
					if (pizza.getName().equals(pizzaComboBox.getSelectionModel().getSelectedItem())) {
						pizzaNameTextField.setText(pizza.getName());
						descriptionTextField.setText(pizza.getDescription());
						weightTextField.setText(pizza.getWeight() + "");
						priceTextField.setText(pizza.getPrice() + "");

						selectedPizza = pizza;
					}
				}

			}
		});

		// klik na edit button
		editButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Editujem!");
				Pizza editedPizza = new Pizza();
				editedPizza.setName(pizzaNameTextField.getText());
				editedPizza.setDescription(descriptionTextField.getText());
				editedPizza.setWeight(Integer.parseInt(weightTextField.getText()));
				editedPizza.setPrice(Double.parseDouble(priceTextField.getText()));

				@SuppressWarnings("unused")
				Pizza pizza = DaoFactory.INSTANCE.getPizzaDao().editPizza(editedPizza);
				openMainAdminPage();

			}
		});

		// klik na back button
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Späť na hlavnu stránku admina!");
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