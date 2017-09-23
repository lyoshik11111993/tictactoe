package com.levelup.tictactoe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;

@Service
public class Game {

    @Autowired
    private GameConnection gameConnection;

    @Autowired
    private Transmit transmit;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private Player player;

    private Random random = new Random();

    private PrintWriter printWriter;

    private BufferedReader bufferedReader;

    private String gameId;

    private String ip;

    private int[][] moves = new int[3][3];

    public void start(boolean isServer, String ip, int port) throws IOException {

        Socket socket = (isServer) ? gameConnection.serverConnection(port) : gameConnection.clientConnection(ip, port);
        System.out.println( (isServer) ? "Server started" : "Client started");
        gameId = UUID.randomUUID().toString();
        this.ip = ip;
        printWriter = new PrintWriter(socket.getOutputStream());
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        int cubeValue;
        int cubeOther;

        do {
            cubeValue = random.nextInt(100);
            transmit.send(String.valueOf(cubeValue), printWriter);
            cubeOther = Integer.parseInt(transmit.receive(bufferedReader));
        } while (cubeOther == cubeValue);

        if (cubeValue > cubeOther) {
            makeMove();
        } else {
            waitMove();
        }


    }

    private void waitMove() throws IOException {
        System.out.println("Wait for move");
        String move = transmit.receive(bufferedReader);
        try {
            historyService.save(gameId, ip, Integer.parseInt(move));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        saveMove(Integer.parseInt(move), false);
        if (checkWin()) {
            System.out.println("You lose!");
        } else if (checkDraw()) {
            System.out.println("Draw!");
        } else {
            makeMove();
        }
    }


    private void makeMove() throws IOException {
        System.out.println("Make a move");
        int move = player.makeMove();
        transmit.send(String.valueOf(move), printWriter);
        try {
            historyService.save(gameId, "local", move);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        saveMove(move, true);
        if (checkWin()) {
            System.out.println("You win!");
        } else if (checkDraw()) {
            System.out.println("Draw!");
        } else {
            waitMove();
        }

    }

    private void saveMove(int move, boolean mine) {
        Pair<Integer, Integer> coords = getCoords(move);
        moves[coords.getSecond()][coords.getFirst()] = (mine) ? 1 : 2;
        printField();
    }

    private void printField() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print((moves[i][j] == 1 ? "X": moves[i][j] == 2 ? "0" : " ") +" |");
            }
            System.out.println();
        }
    }

    private boolean checkWin() {
        for (int i = 1; i < 5; i++) {
            if (checkLines(i)) {
                return true;
            }
        }
        return checkLines(7);
    }

    private boolean checkLines(int i) {
        Pair<Integer, Integer> coords = getCoords(i);
        int x = coords.getFirst();
        int y = coords.getSecond();

        if (y == 0) {
            int sum = 0;
            sum += moves[0][x] == 0 ? -100 : moves[0][x];
            sum += moves[y + 1][x] == 0 ? -100 : moves[y + 1][x];
            sum += moves[y + 2][x] == 0 ? -100 : moves[y + 2][x];
            if (sum == 6 || sum == 3) {
                return true;
            }
        }

        if (x == 0) {
            int sum = 0;
            sum += moves[y][x] == 0 ? -100 : moves[y][x];
            sum += moves[y][x + 1] == 0 ? -100 : moves[y][x + 1];
            sum += moves[y][x + 2] == 0 ? -100 : moves[y][x + 2];
            if (sum == 6 || sum == 3) {
                return true;
            }
        }

        if (x == 0 && y == 0) {
            int sum = 0;
            sum += moves[y][x] == 0 ? -100 : moves[y][x];
            sum += moves[y + 1][x + 1] == 0 ? -100 : moves[y + 1][x + 1];
            sum += moves[y + 2][x + 2] == 0 ? -100 : moves[y + 2][x + 2];
            if (sum == 6 || sum == 3) {
                return true;
            }
        }

        if (x == 2 && y == 0) {
            int sum = 0;
            sum += moves[y][x] == 0 ? -100 : moves[y][x];
            sum += moves[y + 1][x - 1] == 0 ? -100 : moves[y + 1][x - 1];
            sum += moves[y + 2][x - 2] == 0 ? -100 : moves[y + 2][x - 2];
            if (sum == 6 || sum == 3) {
                return true;
            }
        }

        return false;
    }


    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (moves[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private Pair<Integer, Integer> getCoords(int move) {
        int y = (move - 1) / 3;
        int x = move - y * 3 - 1;
        return Pair.of(x, y);
    }

}
