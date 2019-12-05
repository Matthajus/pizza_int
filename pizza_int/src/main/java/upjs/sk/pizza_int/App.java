package upjs.sk.pizza_int;

import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static Stage stage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		stage = primaryStage;
		LoginPageController controller = new LoginPageController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginPage.fxml"));
		fxmlLoader.setController(controller);
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		stage.setTitle("Pizza - INT");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		List<User> user = (DaoFactory.INSTANCE.getUserDao().getAll());
		for (User user2 : user) {
			System.out.println(user2.toString());
		}

		List<Pizza> pizza = DaoFactory.INSTANCE.getPizzaDao().getAll();
		for (Pizza pizza2 : pizza) {
			System.out.println(pizza2.toString());
		}

		List<Order> order = DaoFactory.INSTANCE.getOrderDao().getAll();
		for (Order order2 : order) {
			System.out.println(order2.toString());
		}

		System.out.println(DaoFactory.INSTANCE.getUserDao().getUserByLogin("rico", "123"));

		System.out.println(DaoFactory.INSTANCE.getPizzaDao().getById(3));

		launch(args);

	}

}
