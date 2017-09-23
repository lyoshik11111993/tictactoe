package com.levelup.tictactoe.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@Builder
public class HistoryEntity {
    private int id;
    private String gameId;
    private String playerIp;
    private int cellId;
    private Timestamp createdAt;
}
