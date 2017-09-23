package com.levelup.tictactoe;

import com.levelup.tictactoe.service.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("TicTacToe.xml");
        try {
            ((Game) applicationContext.getBean(Game.class)).start(Boolean.parseBoolean(args[0]), args[1], Integer.parseInt(args[2]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
