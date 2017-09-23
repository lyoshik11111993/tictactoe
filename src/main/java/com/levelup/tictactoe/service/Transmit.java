package com.levelup.tictactoe.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public interface Transmit {

    void send(String data, PrintWriter printWriter);

    String receive(BufferedReader bufferedReader) throws IOException;

}
