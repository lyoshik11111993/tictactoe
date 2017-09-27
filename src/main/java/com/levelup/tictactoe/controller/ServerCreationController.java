package com.levelup.tictactoe.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;

@Component
public class ServerCreationController {

    @FXML
    private Pane pane;

    @FXML
    private TextField ipAdress;

    @FXML
    private TextField port;

    @FXML
    public void initialize() throws IOException {
        ipAdress.setText(InetAddress.getLocalHost().getHostAddress());
        port.setText("8080");
        if (ClientConnectionController.conected==true) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("JavaFX/Game.fxml"));
            pane.getChildren().setAll(root);
        }
    }

    @FXML
    public void close() throws IOException {
        Platform.exit();
    }
}
