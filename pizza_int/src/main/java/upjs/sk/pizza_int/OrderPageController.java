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
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.Order;
import upjs.sk.pizza_int.Models.Pizza;

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
		// zistujeme ci je vybrana hodnota v comboBoxe, defaultne nastavime
		// confirmButton na nie stlacitelny
		final boolean isMyComboBoxEmpty = addressComboBox.getSelectionModel().isEmpty();
		confirmButton.setDisable(true);

		// listener na confirmButton, ak je nieco v comboBoxe vybrane, vtedy mozeme
		// stlacit confirmButton
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
				totalSumLabel.setText(getTotalPrice() + "");
				buyButton.setDisable(false);
			}
		});

		// klik na buy button, po jeho stlaceni zapiseme objednavku do databazy a
		// odosleme mail
		buyButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("potvrdený nákup");

				// zaspi do databazy
				for (Pizza pizza : MainWindowController.myOrder) {
					Order addOrder = new Order();
					addOrder.setAdress(addressComboBox.getValue());
					addOrder.setIdPizza(pizza.getId());
					addOrder.setIdUser(LoginPageController.loggedUser.getId());
					
					// to aby nebol vykricnik, ze sa user nikte nepouziva :D
					@SuppressWarnings("unused")
					Order order = DaoFactory.INSTANCE.getOrderDao().saveOrder(addOrder);

				}

				// odosielanie mailu
				SendMail.send();
				MainWindowController.orderStage.getScene().getWindow().hide();
			}
		});
	}

	public double getTotalPrice() {
		double price = 0;
		for (Pizza pizza : MainWindowController.myOrder) {
			System.out.println(pizza.getPrice());
			price += pizza.getPrice();
		}
		return price;
	}

	public static String orderToString() {
		String result = "";
		for (Pizza pizza : MainWindowController.myOrder) {
			result += pizza.getName() + " -> " + pizza.getDescription() + " " + pizza.getWeight()
					+ pizza.getWeightType() + "\n";
		}
		double price = 0;
		for (Pizza pizza : MainWindowController.myOrder) {
			System.out.println(pizza.getPrice());
			price += pizza.getPrice();
		}
		result += "Total price: " + price + " euro";
		return result;
	}
}