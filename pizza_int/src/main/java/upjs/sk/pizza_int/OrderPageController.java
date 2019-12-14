package upjs.sk.pizza_int;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class OrderPageController {

	@FXML
	private ListView<Pizza> ordersListView;

	@FXML
	private Button buyButton;

	@FXML
	private ComboBox<String> addressComboBox = new ComboBox<String>();

	@FXML
	private Label totalSumLabel;

	@FXML
	private Button confirmButton;

	@FXML
	void initialize() {
		System.out.println("Okno objednavok sa otvorilo!");
		// nastavime buyButton na nie stlacitelny
		buyButton.setDisable(true);
		
		// order list vyplnime pizzami ktore sme si zvolili
		ordersListView.setItems(MainWindowController.myOrder);
		
		// do comboBoxu vlozime hodnoty /intraky kde budeme dovazat pizzu
		addressComboBox.getItems().addAll("Medická 6", "Medická 4", "Popradská 76", "Popradská 66");
		// zistujeme ci je vybrana hodnota v comboBoxe, defaultne nastavime confirmButton na nie stlacitelny
		final boolean isMyComboBoxEmpty = addressComboBox.getSelectionModel().isEmpty();
		confirmButton.setDisable(true);
		
		// listener na confirmButton, ak je nieco v comboBoxe vybrane, vtedy mozeme stlacit confirmButton
		addressComboBox.valueProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isMyComboBoxEmpty) {
					confirmButton.setDisable(false);
				}
			}
		});

		// klik na confirm button, po jeho stlaceni nastavime buyButton na stlacitelny
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("objednávka potvrdená");
				totalSumLabel.setText(getTotalPrice() + " euro");
				buyButton.setDisable(false);
			}
		});
		
		// klik na buy button, po jeho stlaceni zapiseme objednavku do databazy a odosleme mail
				confirmButton.setOnAction(new EventHandler<ActionEvent>() {

					public void handle(ActionEvent event) {
						System.out.println("potvrdený nákup");
						// ... treba doplnit zaspi do databazy
						// ... odosielanie mailu
					}
				});
	}

	public double getTotalPrice() {
		double price = 0;
		for (Pizza pizza : MainWindowController.myOrder) {
			price += pizza.getPrice();
		}
		return price;
	}
}