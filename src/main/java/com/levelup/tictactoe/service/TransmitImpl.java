package com.levelup.tictactoe.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@Service
public class TransmitImpl implements Transmit {

    @Override
    public void send(String data, PrintWriter printWriter) {
        printWriter.println(data);
        printWriter.flush();
    }

    @Override
    public String receive(BufferedReader bufferedReader) throws IOException {
        return bufferedReader.readLine();
    }
}
