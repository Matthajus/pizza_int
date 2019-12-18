package upjs.sk.pizza_int;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.Order;

class MysqlOrderDaoTest {
    static Long orderPred;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        orderPred = (long) DaoFactory.INSTANCE.getOrderDao().getAllForHistory().size();

        Order order = new Order();
        Date date = new Date(System.currentTimeMillis());

        order.setDate(date);
        order.setAdress("Medická 6");
        order.setIdPizza((long) 2);
        order.setIdUser((long) 3);

        DaoFactory.INSTANCE.getOrderDao().saveOrder(order);
    }

    @Test
    void getAllForHistoryTest() {
        assertFalse(DaoFactory.INSTANCE.getOrderDao().getAllForHistory().isEmpty());
        assertNotNull(DaoFactory.INSTANCE.getOrderDao().getAllForHistory());
    }

    @Test
    void saveOrderTest() {
        assertEquals(orderPred + 1, DaoFactory.INSTANCE.getOrderDao().getAllForHistory().size());
    }

}