package tictactoe;

import com.levelup.tictactoe.service.HistoryService;
import com.levelup.tictactoe.service.HistoryServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TicTacToeTest {

    @Test
    public void save(){

        ApplicationContext applicationContext =new  ClassPathXmlApplicationContext("TicTacToe.xml");
        HistoryService historyService = (HistoryService) applicationContext.getBean(HistoryService.class);
        try {
            historyService.save("one", "www", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
