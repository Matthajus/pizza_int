package upjs.sk.pizza_int;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.Pizza;

public class MainAdminPageController {

	public static Stage historyStage = new Stage();
	public static Stage usersStage = new Stage();
	public static Stage addStage = new Stage();

	@FXML
	private TableView<Pizza> pizzaListTableView;

	@FXML
	private Button orderButton;

	@FXML
	private MenuBar upperMenuBar;

	@FXML
	private Menu pizzaListMenu;

	@FXML
	private MenuItem addPizzaMenuItem;

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

		if (LoginPageController.loggedUser.getRole() != 1) {
			usersMenuItem.setDisable(true);
		} else {
			usersMenuItem.setDisable(false);
		}

		// vyplnanie pizzaListTableView
		List<Pizza> result = new ArrayList<Pizza>();

		result = DaoFactory.INSTANCE.getPizzaDao().getAll();

		pizzaListTableView.setItems(FXCollections.observableArrayList(result));

		TableColumn<Pizza, String> currencyCol = new TableColumn<Pizza, String>("");
		currencyCol.setCellValueFactory(new PropertyValueFactory<Pizza, String>("Currency"));
		currencyCol.setStyle("-fx-alignment: CENTER-LEFT;");
		pizzaListTableView.getColumns().add(currencyCol);

		TableColumn<Pizza, Double> priceCol = new TableColumn<Pizza, Double>("Price");
		priceCol.setCellValueFactory(new PropertyValueFactory<Pizza, Double>("Price"));
		priceCol.setStyle("-fx-alignment: CENTER-LEFT;");
		pizzaListTableView.getColumns().add(priceCol);

		TableColumn<Pizza, String> weightTypeCol = new TableColumn<Pizza, String>("");
		weightTypeCol.setCellValueFactory(new PropertyValueFactory<Pizza, String>("WeightType"));
		weightTypeCol.setStyle("-fx-alignment: CENTER-LEFT;");
		pizzaListTableView.getColumns().add(weightTypeCol);

		TableColumn<Pizza, Integer> weightCol = new TableColumn<Pizza, Integer>("Weight");
		weightCol.setCellValueFactory(new PropertyValueFactory<Pizza, Integer>("Weight"));
		weightCol.setStyle("-fx-alignment: CENTER-LEFT;");
		pizzaListTableView.getColumns().add(weightCol);

		TableColumn<Pizza, String> descriptionCol = new TableColumn<Pizza, String>("Description");
		descriptionCol.setCellValueFactory(new PropertyValueFactory<Pizza, String>("Description"));
		descriptionCol.setStyle("-fx-alignment: CENTER-LEFT;");
		pizzaListTableView.getColumns().add(descriptionCol);

		TableColumn<Pizza, String> nameCol = new TableColumn<Pizza, String>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<Pizza, String>("name"));
		nameCol.setStyle("-fx-alignment: CENTER-LEFT;");
		pizzaListTableView.getColumns().add(nameCol);

		// dvojklikom si vyberieme pizzu a ulozime do listu
		pizzaListTableView.setRowFactory(new Callback<TableView<Pizza>, TableRow<Pizza>>() {
			public TableRow<Pizza> call(TableView<Pizza> tv) {
				final TableRow<Pizza> row = new TableRow<Pizza>();
				row.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent event) {
						if (event.getClickCount() == 2 && (!row.isEmpty())) {
							Pizza rowData = DaoFactory.INSTANCE.getPizzaDao().getByName(row.getItem().getName());
							System.out.println("klikol som na riadok" + " " + rowData.toString());
							MainWindowController.myOrder.add(rowData);
							new Alert(Alert.AlertType.INFORMATION, "You added pizza into your order: " + rowData.getName()).showAndWait();
						}
					}
				});
				return row;
			}
		});

		// klik na order button
		orderButton.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				System.out.println("idem sa registrovat");
				openOrderPage();
				for (Pizza pizza : MainWindowController.myOrder) {
					System.out.println(pizza.toString());
				}
			}
		});

		historyMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				openHistoryPage();
			}
		});

		usersMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				openUsersPage();
			}
		});

		addPizzaMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				openAddPage();
			}
		});

	}

	// pomocna metoda pre otvorenie okna, kde ludia budu vidiet svoju objednavku
	// vyberu adresu a nasledne potvrdia objednavku
	private void openOrderPage() {
		OrderPageController controller = new OrderPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderPage.fxml"));
		fxmlLoader.setController(controller);
		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			MainWindowController.orderStage.setTitle("Order");
			MainWindowController.orderStage.setScene(scene);
			MainWindowController.orderStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// pomocna metoda pre otvorenie okna, kde admin bude vidiet historiu objednavok
	private void openHistoryPage() {
		HistoryPageController controller = new HistoryPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistoryPage.fxml"));
		fxmlLoader.setController(controller);
		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			historyStage.setTitle("History");
			historyStage.setScene(scene);
			historyStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// pomocna metoda pre otvorenie okna, kde hlavny admin (root) bude vidiet
	// vsetkych uzivatelov
	// a bude moct niekoho povysit na admina alebo mu odobrat tuto funkciu
	private void openUsersPage() {
		UsersPageController controller = new UsersPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UsersPage.fxml"));
		fxmlLoader.setController(controller);
		try {
			Parent parent = fxmlLoader.load();
			Scene scene = new Scene(parent);
			usersStage.setTitle("Users");
			usersStage.setScene(scene);
			usersStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// pomocna metoda pre otvorenie okna, kde admini budu moct pridavat pizze do
	// zoznamu
	private void openAddPage() {
		AddPageController controller = new AddPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPage.fxml"));
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
