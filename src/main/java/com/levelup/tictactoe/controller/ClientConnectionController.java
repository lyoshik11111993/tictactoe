package com.levelup.tictactoe.controller;

import com.levelup.tictactoe.service.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ClientConnectionController {

    public static boolean conected = false;

    @Autowired
    Game game;

    @FXML
    private Pane pane;

    @FXML
    private TextField ipAdress;

    @FXML
    private TextField port;

    @FXML
    private void connect() throws IOException {
        try {
            game.start(false, ipAdress.getText(), Integer.parseInt(port.getText()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        conected = true;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("JavaFX/Game.fxml"));
        pane.getChildren().setAll(root);
    }
}
