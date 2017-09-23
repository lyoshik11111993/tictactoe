package com.levelup.tictactoe.repository;

import com.levelup.tictactoe.domain.HistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class HistoryRepository {

    @Autowired
    @Qualifier("historyStatement")
    private PreparedStatement preparedStatement;

    public void save(HistoryEntity historyEntity) throws SQLException {

        preparedStatement.setString(1, historyEntity.getGameId());
        preparedStatement.setString(2, historyEntity.getPlayerIp());
        preparedStatement.setInt(3, historyEntity.getCellId());
        preparedStatement.execute();
    }
}
