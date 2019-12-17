package upjs.sk.pizza_int;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import upjs.sk.pizza_int.Dao.DaoFactory;

public class HistoryPageController {

    @FXML
    private ListView<String> ordersListView;

    @FXML
    void initialize() {
        System.out.println("História objednávok sa zobrazila!");
        
        ordersListView.setItems(DaoFactory.INSTANCE.getOrderDao().getAllForHistory());
        
    }
}
