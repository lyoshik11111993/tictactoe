package com.levelup.tictactoe.service;

import java.sql.SQLException;

public interface HistoryService {
    void save(String gameId, String playerIp, int cellId) throws SQLException;
}
