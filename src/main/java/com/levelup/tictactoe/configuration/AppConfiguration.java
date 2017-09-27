package com.levelup.tictactoe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/TicTacToe?serverTimezone=Europe/Kiev", "root", "");
    }

    @Bean(name = "historyStatement")
    public PreparedStatement preparedStatement() throws SQLException {
        return getConnection().prepareStatement(
                "INSERT INTO history (game_id, player_ip, cell_id) VALUES (?, ?, ?)");
    }
}
