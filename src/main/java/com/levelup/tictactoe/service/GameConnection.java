package com.levelup.tictactoe.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Service
public class GameConnection {

    public Socket serverConnection(int port) throws IOException {

        return new ServerSocket(port).accept();

    }

    public Socket clientConnection(String ip, int port) throws IOException {

        return new Socket(ip, port);

    }
}
