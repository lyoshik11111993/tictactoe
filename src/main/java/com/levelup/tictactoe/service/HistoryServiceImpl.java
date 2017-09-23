package com.levelup.tictactoe.service;

import com.levelup.tictactoe.domain.HistoryEntity;
import com.levelup.tictactoe.repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public void save(String gameId, String playerIp, int cellId) throws SQLException {
        historyRepository.save(HistoryEntity.builder().gameId(gameId).playerIp(playerIp).cellId(cellId).build());
    }
}
