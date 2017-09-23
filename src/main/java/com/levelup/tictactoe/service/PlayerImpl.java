package com.levelup.tictactoe.service;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class PlayerImpl implements Player {

    @Override
    public int makeMove() {

        Scanner scanner = new Scanner(System.in);

         return scanner.nextInt() ;
    }
}
