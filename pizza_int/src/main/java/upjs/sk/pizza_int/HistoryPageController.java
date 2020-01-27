package upjs.sk.pizza_int;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import upjs.sk.pizza_int.Dao.DaoFactory;

public class HistoryPageController {

	private int selectedOrderId;

	@FXML
	private ListView<String> ordersListView;

	@FXML
	private Button deleteOrderButton;

	@FXML
	void initialize() {
		System.out.println("História objednávok sa zobrazila!");

		ordersListView.setItems(DaoFactory.INSTANCE.getOrderDao().getAllForHistory());
		ordersListView.setStyle("-fx-font-weight: bold;");

		// dvojklikom na tabulku si vyberieme riadok
		ordersListView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent click) {

				if (click.getClickCount() == 2) {

					String selectedOrder = ordersListView.getSelectionModel().getSelectedItem();
					System.out.println(selectedOrder);

					// ziskanie Id danej objednavky
					String arr[] = selectedOrder.split("	", 2);

					String firstWord = arr[0];

					selectedOrderId = Integer.parseInt(firstWord);
					System.out.println(selectedOrderId);
					new Alert(Alert.AlertType.INFORMATION, "You select order with ID: " + selectedOrderId)
							.showAndWait();
				}
			}
		});

		// klik na deleteOrder button
		deleteOrderButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("Vymazať objednávku!");

				if (selectedOrderId != 0) {
					deleteOrder(selectedOrderId);
				} else {
					new Alert(Alert.AlertType.INFORMATION, "You have to select any row (double click). ").showAndWait();
				}
			}
		});

	}

	public void deleteOrder(int orderID) {
		DaoFactory.INSTANCE.getOrderDao().deletOrder(orderID);
		ordersListView.setItems(DaoFactory.INSTANCE.getOrderDao().getAllForHistory());
		ordersListView.setStyle("-fx-font-weight: bold;");
	}
}
