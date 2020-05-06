package com.example.tictactoe;

import java.util.Random;

class AiMoveGenerator {
    private final Random random = new Random();
    private Board board;

    public AiMoveGenerator(Board board){
        this.board = board;
    }

    public Integer getMove(){
        if(board.isFull()){
            return null;
        }

        while(true){
            int iaMove = new Random().nextInt(9  );
            if(board.isEmpty(iaMove)){
                return iaMove;
            }
        }
    }
}
