package com.levelup.tictactoe.controller;

import com.levelup.tictactoe.service.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
public class MainController {

    @Autowired
    private Game game;

    @FXML
    private Pane gamePane;

    @FXML
    private void createServer() throws IOException {
        game.start(true, InetAddress.getLocalHost().getHostAddress(), 8080);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("JavaFX/ServerCreation.fxml"));
        gamePane.getChildren().setAll(root);
    }

    @FXML
    private void connectToServer() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("JavaFX/ClientConnection.fxml"));
        gamePane.getChildren().setAll(root);
    }
}
