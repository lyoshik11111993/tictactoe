package com.levelup.tictactoe;

import com.levelup.tictactoe.service.Game;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MainController {

    @Autowired
    Game game;

    @FXML
    Rectangle cell00;


    @FXML
    void cellClicked() {
        cell00.setFill(Color.DARKGREEN);
    }

    private Node view;


    public Node getView() {
        return view;
    }

    public void setView (Node view){
        this.view = view;
    }
}
