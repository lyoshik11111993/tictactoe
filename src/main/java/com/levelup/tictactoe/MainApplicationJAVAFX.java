package com.levelup.tictactoe;

import com.levelup.tictactoe.service.Game;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class MainApplicationJAVAFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainController controller = SpringFXMLLoader.load("/main.fxml");
        Scene scene = new Scene((Parent) controller.getView(), 300, 275);
        primaryStage.setTitle("App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
