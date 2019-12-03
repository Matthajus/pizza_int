package upjs.sk.pizza_int;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage stage) throws Exception {

		Parent rootPane = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));

//		Button button = new Button("Stlač ma!");
//		AnchorPane rootPane = new AnchorPane();
//		rootPane.getChildren().add(button);
//		rootPane.setPrefSize(400, 300);
		Scene scene = new Scene(rootPane);
		stage.setTitle("Hello world");
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		List<User> user = (DaoFactory.INSTANCE.getUserDao().getAll());
		for (User user2 : user) {
			System.out.println(user2.toString());
		}
		
		System.out.println(DaoFactory.INSTANCE.getUserDao().getUserByLogin("rico", "123"));

		launch(args);

	}

}
